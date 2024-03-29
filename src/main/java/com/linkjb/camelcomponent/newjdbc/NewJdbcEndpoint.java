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

import com.linkjb.camelcomponent.dto.JdbcDTO;
import org.apache.camel.*;
import org.apache.camel.spi.Metadata;
import org.apache.camel.support.DefaultEndpoint;

import javax.sql.DataSource;
import java.util.Map;

/**
 * Access databases through SQL and JDBC.
 */
//@UriEndpoint(firstVersion = "1.2.0", scheme = "sharkjdbc", title = "JDBC", syntax = "jdbc:dataSourceName", producerOnly = true,
//             category = { Category.DATABASE, Category.SQL })
public class NewJdbcEndpoint extends DefaultEndpoint {

    private DataSource dataSource;

    ///@UriPath
    @Metadata(required = true)
    private String dataSourceName;
    //@UriParam
    private int readSize;
    //@UriParam
    private boolean transacted;
    //@UriParam(defaultValue = "true")
    private boolean resetAutoCommit = true;
    //@UriParam(prefix = "statement.", multiValue = true)
    private Map<String, Object> parameters;
    //@UriParam(defaultValue = "true")
    private boolean useJDBC4ColumnNameAndLabelSemantics = true;
    //@UriParam
    private boolean useGetBytesForBlob;
    //@UriParam(label = "advanced")
    private NewJdbcPrepareStatementStrategy prepareStatementStrategy = new NewDefaultJdbcPrepareStatementStrategy();
    //@UriParam(defaultValue = "true")
    private boolean allowNamedParameters = true;
    //@UriParam
    private boolean useHeadersAsParameters;
    //@UriParam(defaultValue = "SelectList")
    private JdbcOutputType outputType = JdbcOutputType.SelectList;
    //@UriParam
    private String outputClass;
    //@UriParam(label = "advanced")
    private BeanRowMapper beanRowMapper = new DefaultBeanRowMapper();
    //@UriParam(label = "advanced")
    private ConnectionStrategy connectionStrategy = new DefaultConnectionStrategy();

    private JdbcDTO jdbcDTO;

    public NewJdbcEndpoint() {
    }

    public NewJdbcEndpoint(String endpointUri, Component component, DataSource dataSource) {
        super(endpointUri, component);
        this.dataSource = dataSource;
    }

    public NewJdbcEndpoint(String endpointUri, Component component, JdbcDTO jdbcDTO) {
        super(endpointUri, component);
        this.jdbcDTO = jdbcDTO;
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        throw new UnsupportedOperationException("sharkjdbc组件不支持作为consumer使用");
    }

    @Override
    public Producer createProducer() throws Exception {
        return new NewJdbcProducer(this, dataSource, connectionStrategy, readSize, parameters, jdbcDTO);
    }

    public String getDataSourceName() {
        return dataSourceName;
    }

    /**
     * Name of DataSource to lookup in the Registry. If the name is dataSource or default, then Camel will attempt to
     * lookup a default DataSource from the registry, meaning if there is a only one instance of DataSource found, then
     * this DataSource will be used.
     */
    public void setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }

    public int getReadSize() {
        return readSize;
    }

    /**
     * The default maximum number of rows that can be read by a polling query. The default value is 0.
     */
    public void setReadSize(int readSize) {
        this.readSize = readSize;
    }

    public boolean isTransacted() {
        return transacted;
    }

    /**
     * Whether transactions are in use.
     */
    public void setTransacted(boolean transacted) {
        this.transacted = transacted;
    }

    public boolean isResetAutoCommit() {
        return resetAutoCommit;
    }

    /**
     * Camel will set the autoCommit on the JDBC connection to be false, commit the change after executed the statement
     * and reset the autoCommit flag of the connection at the end, if the resetAutoCommit is true. If the JDBC
     * connection doesn't support to reset the autoCommit flag, you can set the resetAutoCommit flag to be false, and
     * Camel will not try to reset the autoCommit flag. When used with XA transactions you most likely need to set it to
     * false so that the transaction manager is in charge of committing this tx.
     */
    public void setResetAutoCommit(boolean resetAutoCommit) {
        this.resetAutoCommit = resetAutoCommit;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    /**
     * The data source to use
     */
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    /**
     * Optional parameters to the {@link java.sql.Statement}.
     * <p/>
     * For example to set maxRows, fetchSize etc.
     *
     * @param parameters parameters which will be set using reflection
     */
    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    public boolean isUseJDBC4ColumnNameAndLabelSemantics() {
        return useJDBC4ColumnNameAndLabelSemantics;
    }

    /**
     * Sets whether to use JDBC 4 or JDBC 3.0 or older semantic when retrieving column name.
     * <p/>
     * JDBC 4.0 uses columnLabel to get the column name where as JDBC 3.0 uses both columnName or columnLabel.
     * Unfortunately JDBC drivers behave differently so you can use this option to work out issues around your JDBC
     * driver if you get problem using this component
     * <p/>
     * This option is default <tt>true</tt>.
     *
     * @param useJDBC4ColumnNameAndLabelSemantics <tt>true</tt> to use JDBC 4.0 semantics, <tt>false</tt> to use JDBC
     *                                            3.0.
     */
    public void setUseJDBC4ColumnNameAndLabelSemantics(boolean useJDBC4ColumnNameAndLabelSemantics) {
        this.useJDBC4ColumnNameAndLabelSemantics = useJDBC4ColumnNameAndLabelSemantics;
    }

    public NewJdbcPrepareStatementStrategy getPrepareStatementStrategy() {
        return prepareStatementStrategy;
    }

    public void setPrepareStatementStrategy(NewJdbcPrepareStatementStrategy prepareStatementStrategy) {
        this.prepareStatementStrategy = prepareStatementStrategy;
    }

    public boolean isAllowNamedParameters() {
        return allowNamedParameters;
    }

    /**
     * whether to allow using named parameters in the queries.
     */
    public void setAllowNamedParameters(boolean allowNamedParameters) {
        this.allowNamedParameters = allowNamedParameters;
    }

    public boolean isUseHeadersAsParameters() {
        return useHeadersAsParameters;
    }

    /**
     * Set this option to true to use the prepareStatementStrategy with named parameters. This allows to define queries
     * with named placeholders, and use headers with the dynamic values for the query placeholders.
     */
    public void setUseHeadersAsParameters(boolean useHeadersAsParameters) {
        this.useHeadersAsParameters = useHeadersAsParameters;
    }

    public JdbcOutputType getOutputType() {
        return outputType;
    }

    /**
     * Determines the output the producer should use.
     */
    public void setOutputType(JdbcOutputType outputType) {
        this.outputType = outputType;
    }

    public String getOutputClass() {
        return outputClass;
    }

    /**
     * Specify the full package and class name to use as conversion when outputType=SelectOne or SelectList.
     */
    public void setOutputClass(String outputClass) {
        this.outputClass = outputClass;
    }

    public BeanRowMapper getBeanRowMapper() {
        return beanRowMapper;
    }

    /**
     * To use a custom BeanRowMapper when using outputClass. The default implementation
     * will lower case the row names and skip underscores, and dashes. For example "CUST_ID" is mapped as "custId".
     */
    public void setBeanRowMapper(BeanRowMapper beanRowMapper) {
        this.beanRowMapper = beanRowMapper;
    }

    public boolean isUseGetBytesForBlob() {
        return this.useGetBytesForBlob;
    }

    /**
     * To read BLOB columns as bytes instead of string data.
     * <p/>
     * This may be needed for certain databases such as Oracle where you must read BLOB columns as bytes.
     */
    public void setUseGetBytesForBlob(boolean useGetBytesForBlob) {
        this.useGetBytesForBlob = useGetBytesForBlob;
    }

    public ConnectionStrategy getConnectionStrategy() {
        return connectionStrategy;
    }

    /**
     * To use a custom strategy for working with connections.
     * <p>
     * Do not use a custom strategy when using the spring-jdbc component because a special Spring ConnectionStrategy is
     * used by default to support Spring Transactions.
     */
    public void setConnectionStrategy(ConnectionStrategy connectionStrategy) {
        this.connectionStrategy = connectionStrategy;
    }

    public JdbcDTO getJdbcDTO() {
        return jdbcDTO;
    }

    public void setJdbcDTO(JdbcDTO jdbcDTO) {
        this.jdbcDTO = jdbcDTO;
    }

    @Override
    protected String createEndpointUri() {
        return dataSourceName != null ? "jdbc:" + dataSourceName : "jdbc";
    }
}
