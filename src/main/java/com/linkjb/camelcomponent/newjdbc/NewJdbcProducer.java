/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.linkjb.camelcomponent.newjdbc;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.linkjb.camelcomponent.dto.JdbcDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.ExtendedExchange;
import org.apache.camel.spi.Synchronization;
import org.apache.camel.support.DefaultProducer;
import org.apache.camel.support.PropertyBindingSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

@Slf4j
public class NewJdbcProducer extends DefaultProducer {

    private static final Logger LOG = LoggerFactory.getLogger(NewJdbcProducer.class);

    private final DataSource dataSource;
    private final ConnectionStrategy connectionStrategy;
    private final int readSize;
    private final Map<String, Object> parameters;

    private final JdbcDTO jdbcDTO;

    public NewJdbcProducer(NewJdbcEndpoint endpoint, DataSource dataSource, ConnectionStrategy connectionStrategy,
                           int readSize, Map<String, Object> parameters, JdbcDTO jdbcDTO) throws Exception {
        super(endpoint);
        this.dataSource = dataSource;
        this.connectionStrategy = connectionStrategy;
        this.readSize = readSize;
        this.parameters = parameters;
        this.jdbcDTO = jdbcDTO;
    }

    @Override
    public NewJdbcEndpoint getEndpoint() {
        return (NewJdbcEndpoint) super.getEndpoint();
    }

    /**
     * Execute sql of exchange and set results on output
     */
    @Override
    public void process(Exchange exchange) throws Exception {
        //if (getEndpoint().isResetAutoCommit()) {
        processingSqlBySettingAutoCommit(exchange, null);
        //} else {
        //processingSqlWithoutSettingAutoCommit(exchange);
        //}
    }

    public void process(Exchange exchange, JdbcDTO jdbcDTO) throws Exception {
        //if (getEndpoint().isResetAutoCommit()) {
        processingSqlBySettingAutoCommit(exchange, jdbcDTO);
        //} else {
        //processingSqlWithoutSettingAutoCommit(exchange);
        //}
    }


    private void processingSqlBySettingAutoCommit(Exchange exchange, JdbcDTO jdbcDTO) throws Exception {
        String sql;
        if (jdbcDTO == null) {
            sql = this.jdbcDTO.getSql();
        } else {
            sql = jdbcDTO.getSql();
        }
        //String sql = exchange.getIn().getBody(String.class);
        Connection conn = null;
        Boolean autoCommit = null;
        boolean shouldCloseResources = true;

        try {
            conn = connectionStrategy.getConnection(jdbcDTO == null ? this.jdbcDTO.getDataSource() : jdbcDTO.getDataSource());
            autoCommit = conn.getAutoCommit();
            if (autoCommit) {
                conn.setAutoCommit(false);
            }

            shouldCloseResources = createAndExecuteSqlStatement(exchange, sql, conn, jdbcDTO);

            conn.commit();
        } catch (Exception e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (Throwable sqle) {
                LOG.warn("Error occurred during JDBC rollback. This exception will be ignored.", sqle);
            }
            throw e;
        } finally {
            if (shouldCloseResources) {
                resetAutoCommit(conn, autoCommit);
                closeQuietly(conn);
            }
        }
    }

    private void processingSqlWithoutSettingAutoCommit(Exchange exchange) throws Exception {
        String sql = exchange.getIn().getBody(String.class);
        Connection conn = null;
        boolean shouldCloseResources = true;

        try {
            conn = connectionStrategy.getConnection(dataSource);
            shouldCloseResources = createAndExecuteSqlStatement(exchange, sql, conn, jdbcDTO);
        } finally {
            if (shouldCloseResources && !connectionStrategy.isConnectionTransactional(conn, dataSource)) {
                closeQuietly(conn);
            }
        }
    }

    private boolean createAndExecuteSqlStatement(Exchange exchange, String sql, Connection conn, JdbcDTO jdbcDTO) throws Exception {
        if (jdbcDTO == null ? this.jdbcDTO.isUseHeadersAsParameters() : jdbcDTO.isUseHeadersAsParameters()) {
            return doCreateAndExecuteSqlStatementWithHeadersAndBody(exchange, sql, conn, jdbcDTO);
        } else {
            return doCreateAndExecuteSqlStatement(exchange, sql, conn, jdbcDTO);
        }
    }

    /*
     * @Author shark
     * @Description //使用头或体来替换
     * @Date 2022/4/27
     * @Param
     * @return
     **/
    private boolean doCreateAndExecuteSqlStatementWithHeadersAndBody(Exchange exchange, String sql, Connection conn, JdbcDTO jdbcDTO) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean shouldCloseResources = true;

        try {
            boolean flag;
            if (jdbcDTO == null) {
                flag = this.jdbcDTO.isAllowNamedParameters();
            } else {
                flag = jdbcDTO.isAllowNamedParameters();
            }
            this.jdbcDTO.setPrepareStatementStrategy(new NewDefaultJdbcPrepareStatementStrategy());
            final String preparedQuery
                    = this.jdbcDTO.getPrepareStatementStrategy().prepareQuery(sql,
                    flag
            );
            Boolean shouldRetrieveGeneratedKeys
                    = exchange.getIn().getHeader(NewJdbcConstants.JDBC_RETRIEVE_GENERATED_KEYS, false, Boolean.class);

            if (shouldRetrieveGeneratedKeys) {
                Object expectedGeneratedColumns = exchange.getIn().getHeader(NewJdbcConstants.JDBC_GENERATED_COLUMNS);
                if (expectedGeneratedColumns == null) {
                    ps = conn.prepareStatement(preparedQuery, Statement.RETURN_GENERATED_KEYS);
                } else if (expectedGeneratedColumns instanceof String[]) {
                    ps = conn.prepareStatement(preparedQuery, (String[]) expectedGeneratedColumns);
                } else if (expectedGeneratedColumns instanceof int[]) {
                    ps = conn.prepareStatement(preparedQuery, (int[]) expectedGeneratedColumns);
                } else {
                    throw new IllegalArgumentException(
                            "Header specifying expected returning columns isn't an instance of String[] or int[] but "
                                    + expectedGeneratedColumns.getClass());
                }
            } else {
                ps = conn.prepareStatement(preparedQuery);
            }
            int expectedCount = ps.getParameterMetaData().getParameterCount();
            if (expectedCount > 0) {
                Iterator<?> it = this.jdbcDTO.getPrepareStatementStrategy()
                        .createPopulateIterator(sql, preparedQuery, expectedCount, exchange, exchange.getIn().getBody());
                this.jdbcDTO.getPrepareStatementStrategy().populateStatement(ps, it, expectedCount);
            }
            LOG.debug("Executing JDBC PreparedStatement: {}", sql);

            boolean stmtExecutionResult = ps.execute();
            if (stmtExecutionResult) {
                rs = ps.getResultSet();
                shouldCloseResources = setResultSet(exchange, conn, rs, jdbcDTO);
            } else {
                int updateCount = ps.getUpdateCount();
                // and then set the new header
                exchange.getMessage().setHeader(NewJdbcConstants.JDBC_UPDATE_COUNT, updateCount);
            }

            if (shouldRetrieveGeneratedKeys) {
                setGeneratedKeys(exchange, conn, ps.getGeneratedKeys());
            }
        } finally {
            if (shouldCloseResources) {
                closeQuietly(rs);
                closeQuietly(ps);
            }
        }
        return shouldCloseResources;
    }

    private boolean setResultSet(Exchange exchange, Connection conn, ResultSet rs) throws Exception {
        boolean answer = true;

        ResultSetIterator iterator = new ResultSetIterator(
                conn, rs, this.jdbcDTO.isUseJDBC4ColumnNameAndLabelSemantics(), this.jdbcDTO.isUseGetBytesForBlob());

        JdbcOutputType outputType = this.jdbcDTO.getOutputType();
        exchange.getMessage().setHeader(NewJdbcConstants.JDBC_COLUMN_NAMES, iterator.getColumnNames());
        if (outputType == JdbcOutputType.StreamList) {
            exchange.getMessage()
                    .setBody(new StreamListIterator(
                            getEndpoint().getCamelContext(), getEndpoint().getOutputClass(), getEndpoint().getBeanRowMapper(),
                            iterator));
            exchange.adapt(ExtendedExchange.class).addOnCompletion(new ResultSetIteratorCompletion(iterator));
            // do not close resources as we are in streaming mode
            answer = false;
        } else if (outputType == JdbcOutputType.SelectList) {

            List<Map<String, Object>> list = extractRows(iterator);
            exchange.getMessage().setHeader(NewJdbcConstants.JDBC_ROW_COUNT, list.size());
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put(this.jdbcDTO.getQueryName(), list);
            if (this.jdbcDTO.getChild() != null && this.jdbcDTO.getChild().size() > 0) {
                for (Map<String, Object> map : list) {
                    for (JdbcDTO sonJdbcDTO : this.jdbcDTO.getChild()) {
                        exchange.getMessage().setHeader(NewJdbcConstants.JDBC_PARENT_PARAMETERS, map);
                        exchange.getMessage().setBody(map);
                        this.process(exchange, sonJdbcDTO);
                        map.put(sonJdbcDTO.getQueryName(), exchange.getMessage().getBody());
                    }

                }
            }
            exchange.getMessage().setBody(com.alibaba.fastjson.JSON.toJSONString(resultMap));
        }

        return answer;
    }

    private boolean doCreateAndExecuteSqlStatement(Exchange exchange, String sql, Connection conn, JdbcDTO jdbcDTO) throws Exception {
        Statement stmt = null;
        ResultSet rs = null;
        boolean shouldCloseResources = true;

        try {
            stmt = conn.createStatement();

            if (parameters != null && !parameters.isEmpty()) {
                Map<String, Object> copy = new HashMap<>(parameters);
                PropertyBindingSupport.bindProperties(exchange.getContext(), stmt, copy);
            }

            LOG.debug("Executing JDBC Statement: {}", sql);

            Boolean shouldRetrieveGeneratedKeys
                    = exchange.getIn().getHeader(NewJdbcConstants.JDBC_RETRIEVE_GENERATED_KEYS, false, Boolean.class);

            boolean stmtExecutionResult;
            if (shouldRetrieveGeneratedKeys) {
                Object expectedGeneratedColumns = exchange.getIn().getHeader(NewJdbcConstants.JDBC_GENERATED_COLUMNS);
                if (expectedGeneratedColumns == null) {
                    stmtExecutionResult = stmt.execute(sql, Statement.RETURN_GENERATED_KEYS);
                } else if (expectedGeneratedColumns instanceof String[]) {
                    stmtExecutionResult = stmt.execute(sql, (String[]) expectedGeneratedColumns);
                } else if (expectedGeneratedColumns instanceof int[]) {
                    stmtExecutionResult = stmt.execute(sql, (int[]) expectedGeneratedColumns);
                } else {
                    throw new IllegalArgumentException(
                            "Header specifying expected returning columns isn't an instance of String[] or int[] but "
                                    + expectedGeneratedColumns.getClass());
                }
            } else {
                stmtExecutionResult = stmt.execute(sql);
            }

            if (stmtExecutionResult) {
                rs = stmt.getResultSet();
                //todo
                shouldCloseResources = setResultSet(exchange, conn, rs, jdbcDTO);
            } else {
                int updateCount = stmt.getUpdateCount();
                // and then set the new header
                exchange.getMessage().setHeader(NewJdbcConstants.JDBC_UPDATE_COUNT, updateCount);
            }

            if (shouldRetrieveGeneratedKeys) {
                setGeneratedKeys(exchange, conn, stmt.getGeneratedKeys());
            }
        } finally {
            if (shouldCloseResources) {
                closeQuietly(rs);
                closeQuietly(stmt);
            }
        }
        return shouldCloseResources;
    }

    private void closeQuietly(ResultSet rs) {
        if (rs != null) {
            try {
                if (!rs.isClosed()) {
                    rs.close();
                }
            } catch (Throwable sqle) {
                LOG.debug("Error by closing result set", sqle);
            }
        }
    }

    private void closeQuietly(Statement stmt) {
        if (stmt != null) {
            try {
                if (!stmt.isClosed()) {
                    stmt.close();
                }
            } catch (Throwable sqle) {
                LOG.debug("Error by closing statement", sqle);
            }
        }
    }

    private void resetAutoCommit(Connection con, Boolean autoCommit) {
        if (con != null && autoCommit != null) {
            try {
                con.setAutoCommit(autoCommit);
            } catch (Throwable sqle) {
                LOG.debug("Error by resetting auto commit to its original value", sqle);
            }
        }
    }

    private void closeQuietly(Connection con) {
        if (con != null) {
            try {
                if (!con.isClosed()) {
                    con.close();
                }
            } catch (Throwable sqle) {
                LOG.debug("Error by closing connection", sqle);
            }
        }
    }

    /**
     * Sets the generated if any to the Exchange in headers : - {@link NewJdbcConstants#JDBC_GENERATED_KEYS_ROW_COUNT} :
     * the row count of generated keys - {@link NewJdbcConstants#JDBC_GENERATED_KEYS_DATA} : the generated keys data
     *
     * @param exchange      The exchange where to store the generated keys
     * @param conn          Current JDBC connection
     * @param generatedKeys The result set containing the generated keys
     */
    protected void setGeneratedKeys(Exchange exchange, Connection conn, ResultSet generatedKeys) throws SQLException {
        if (generatedKeys != null) {
            ResultSetIterator iterator = new ResultSetIterator(
                    conn, generatedKeys, getEndpoint().isUseJDBC4ColumnNameAndLabelSemantics(),
                    getEndpoint().isUseGetBytesForBlob());
            List<Map<String, Object>> data = extractRows(iterator);

            exchange.getMessage().setHeader(NewJdbcConstants.JDBC_GENERATED_KEYS_ROW_COUNT, data.size());
            exchange.getMessage().setHeader(NewJdbcConstants.JDBC_GENERATED_KEYS_DATA, data);
        }
    }

    /**
     * Sets the result from the ResultSet to the Exchange as its OUT body.
     *
     * @return whether to close resources
     */
    protected boolean setResultSet(Exchange exchange, Connection conn, ResultSet rs, JdbcDTO jdbcDTO) throws Exception {
        boolean answer = true;

        ResultSetIterator iterator = new ResultSetIterator(
                conn, rs, this.jdbcDTO.isUseJDBC4ColumnNameAndLabelSemantics(), this.jdbcDTO.isUseGetBytesForBlob());

        JdbcOutputType outputType = this.jdbcDTO.getOutputType();
        exchange.getMessage().setHeader(NewJdbcConstants.JDBC_COLUMN_NAMES, iterator.getColumnNames());
        if (outputType == JdbcOutputType.StreamList) {
            exchange.getMessage()
                    .setBody(new StreamListIterator(
                            getEndpoint().getCamelContext(), getEndpoint().getOutputClass(), getEndpoint().getBeanRowMapper(),
                            iterator));
            exchange.adapt(ExtendedExchange.class).addOnCompletion(new ResultSetIteratorCompletion(iterator));
            // do not close resources as we are in streaming mode
            answer = false;
        } else if (outputType == JdbcOutputType.SelectList) {
            if (jdbcDTO == null) {
                List<Map<String, Object>> list = extractRows(iterator);
                exchange.getMessage().setHeader(NewJdbcConstants.JDBC_ROW_COUNT, list.size());
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put(this.jdbcDTO.getQueryName(), list);
                if (this.jdbcDTO.getChild() != null && this.jdbcDTO.getChild().size() > 0) {
                    for (Map<String, Object> map : list) {
                        for (JdbcDTO sonJdbcDTO : this.jdbcDTO.getChild()) {
                            exchange.getMessage().setHeader(NewJdbcConstants.JDBC_PARENT_PARAMETERS, map);
                            exchange.getMessage().setBody(map);
                            this.process(exchange, sonJdbcDTO);
                            map.put(sonJdbcDTO.getQueryName(), exchange.getMessage().getBody());
                        }

                    }
                }
                if(this.jdbcDTO.getOutPutTypeCode().equals(1)){
                    exchange.getMessage().setBody(com.alibaba.fastjson.JSON.toJSONString(resultMap));
                }else if(this.jdbcDTO.getOutPutTypeCode().equals(2)){
                    JSONObject obj = JSONUtil.createObj();
                    obj.putAll(resultMap);
                    String s = JSONUtil.toXmlStr(obj);
                    exchange.getMessage().setBody(s);
                }else{
                    exchange.getMessage().setBody(com.alibaba.fastjson.JSON.toJSONString(resultMap));
                }
            } else {
                List<Map<String, Object>> list = extractRows(iterator);
                exchange.getMessage().setHeader(NewJdbcConstants.JDBC_ROW_COUNT, list.size());
                if (jdbcDTO.getChild() != null && jdbcDTO.getChild().size() > 0) {
                    for (Map<String, Object> map : list) {
                        for (JdbcDTO sonJdbcDTO : jdbcDTO.getChild()) {
                            exchange.getMessage().setHeader(NewJdbcConstants.JDBC_PARENT_PARAMETERS, map);
                            exchange.getMessage().setBody(map);
                            this.process(exchange, sonJdbcDTO);
                            map.put(sonJdbcDTO.getQueryName(), exchange.getMessage().getBody());
                        }

                    }
                }
                exchange.getMessage().setBody(list);
            }
        }

        return answer;
    }


    @SuppressWarnings("unchecked")
    private List extractRows(ResultSetIterator iterator) throws SQLException {
        List result = new ArrayList();
        int maxRowCount = readSize == 0 ? Integer.MAX_VALUE : readSize;
        for (int i = 0; iterator.hasNext() && i < maxRowCount; i++) {
            Map<String, Object> row = iterator.next();
            Object value;
            if (getEndpoint().getOutputClass() != null) {
                value = JdbcHelper.newBeanInstance(getEndpoint().getCamelContext(), getEndpoint().getOutputClass(),
                        getEndpoint().getBeanRowMapper(), row);
            } else {
                value = row;
            }
            result.add(value);
        }
        return result;
    }

    private Object extractSingleRow(ResultSetIterator iterator) throws SQLException {
        if (!iterator.hasNext()) {
            return null;
        }

        Map<String, Object> row = iterator.next();
        if (iterator.hasNext()) {
            throw new SQLDataException("Query result not unique for outputType=SelectOne.");
        } else if (getEndpoint().getOutputClass() != null) {
            return JdbcHelper.newBeanInstance(getEndpoint().getCamelContext(), getEndpoint().getOutputClass(),
                    getEndpoint().getBeanRowMapper(), row);
        } else if (row.size() == 1) {
            return row.values().iterator().next();
        } else {
            return row;
        }
    }

    private static final class ResultSetIteratorCompletion implements Synchronization {
        private final ResultSetIterator iterator;

        private ResultSetIteratorCompletion(ResultSetIterator iterator) {
            this.iterator = iterator;
        }

        @Override
        public void onComplete(Exchange exchange) {
            iterator.close();
            iterator.closeConnection();
        }

        @Override
        public void onFailure(Exchange exchange) {
            iterator.close();
            iterator.closeConnection();
        }
    }
}
