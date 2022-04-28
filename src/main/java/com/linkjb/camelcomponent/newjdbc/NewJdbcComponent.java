package com.linkjb.camelcomponent.newjdbc;

import com.alibaba.fastjson.JSON;
import com.linkjb.camelcomponent.dto.JdbcDTO;
import com.linkjb.camelcomponent.jdbc.ConnectionStrategy;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.NoSuchBeanException;
import org.apache.camel.spi.Metadata;
import org.apache.camel.spi.annotations.Component;
import org.apache.camel.support.CamelContextHelper;
import org.apache.camel.support.DefaultComponent;
import org.apache.camel.util.PropertiesHelper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName JdbcComponent
 * @Description 自研JDBC组件
 * @Author shark
 * @Data 2022/4/22 14:05
 **/
@Component("sharkjdbc")
@Slf4j
public class NewJdbcComponent extends DefaultComponent {

    @Autowired
    CamelContextHelper camelContextHelper;
    @Autowired
    CamelContext camelContext;
    @Metadata(label = "advanced")
    private ConnectionStrategy connectionStrategy;

    private static boolean isDefaultDataSourceName(String remaining) {
        return "dataSource".equalsIgnoreCase(remaining) || "default".equalsIgnoreCase(remaining);
    }

    @Override
    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        JdbcDTO jdbcDTO = null;
        if (remaining == null) {
            throw new IllegalArgumentException("使用sharkJdbc组件必须使用封装jdbcDto");
        } else {
            jdbcDTO = JSON.parseObject(remaining, JdbcDTO.class);
            getDataSourceSet(jdbcDTO);
        }

        NewJdbcEndpoint jdbc = createEndpoint(uri, this, jdbcDTO);
        if (connectionStrategy != null) {
            jdbc.setConnectionStrategy(connectionStrategy);
        }
        return jdbc;
    }

    /*
     *
     * @Author shark
     * @Description //创建EndPoint
     * @Date 2022/4/25
     * @Param [uri, component, jdbcDTO]
     * @return com.linkjb.camelcomponent.newjdbc.NewJdbcEndpoint
     **/
    protected NewJdbcEndpoint createEndpoint(String uri, NewJdbcComponent component, JdbcDTO jdbcDTO) {
        return new NewJdbcEndpoint(uri, component, jdbcDTO);
    }

    /*
     *
     * @Author shark
     * @Description //递归获取Datasource
     * @Date 2022/4/25
     * @Param [jdbcDTO, set]
     * @return void
     **/
    private void getDataSourceSet(JdbcDTO jdbcDTO) {
        //url.split(":")
        String dataSourceId = jdbcDTO.getDataSourceId();
        if (dataSourceId == null) {
            throw new IllegalArgumentException("dataSourceId不可为空,请检查参数");
        } else {
            DataSource target = CamelContextHelper.lookup(getCamelContext(), dataSourceId, DataSource.class);
            if (target == null && !isDefaultDataSourceName(dataSourceId)) {
                throw new NoSuchBeanException(dataSourceId, DataSource.class.getName());
            } else if (target == null) {
                // check if the registry contains a single instance of DataSource
                Set<DataSource> dataSources = getCamelContext().getRegistry().findByType(DataSource.class);
                if (dataSources.size() > 1) {
                    throw new IllegalArgumentException(
                            "Multiple DataSources found in the registry and no explicit configuration provided");
                } else if (dataSources.size() == 1) {
                    target = dataSources.iterator().next();
                    jdbcDTO.setDataSource(target);
                    jdbcDTO.setParamter(PropertiesHelper.extractProperties(jdbcDTO.getParamter(), "statement."));
                }
                if (target == null) {
                    throw new IllegalArgumentException("No default DataSource found in the registry");
                }
                log.info("从camelContext中获取数据源: {}", target);
            }
            if (jdbcDTO.getChild() != null && jdbcDTO.getChild().size() > 0) {
                for (JdbcDTO sonDto : jdbcDTO.getChild()) {
                    getDataSourceSet(sonDto);
                }
            }
        }
    }
}
