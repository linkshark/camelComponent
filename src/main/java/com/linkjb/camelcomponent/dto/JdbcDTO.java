package com.linkjb.camelcomponent.dto;

import com.linkjb.camelcomponent.jdbc.*;
import com.linkjb.camelcomponent.newjdbc.NewDefaultJdbcPrepareStatementStrategy;
import com.linkjb.camelcomponent.newjdbc.NewJdbcPrepareStatementStrategy;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * @ClassName JdbcDto
 * @Description jdbcDTO 后端使用时重构为json
 * @Author shark
 * @Data 2022/4/21 10:43
 **/
@Data
@ApiModel(value = "JDBC-DTO")
public class JdbcDTO {
    @ApiModelProperty("查询名称")
    private String queryName;
    @ApiModelProperty("查询URL")
    //statement特殊处理
    private Map<String, Object> paramter;
    @ApiModelProperty("数据源ID")
    private String dataSourceId;
    @ApiModelProperty("sql语句")
    private String sql;
    @ApiModelProperty("子查询")
    private List<JdbcDTO> child;
    @ApiModelProperty("指定数据源")
    private DataSource dataSource;
    @ApiModelProperty("轮询查询可以读取的默认最大行数。 默认值为 0。")
    private int readSize;
    @ApiModelProperty("是否启动事务,默认启动")
    private boolean transacted;
    @ApiModelProperty("Camel 会将 JDBC 连接上的 autoCommit 设置为 false，如果 resetAutoCommit 为 true，则在执行语句后提交更改并在最后重置连接的 autoCommit 标志。 如果 JDBC 连接不支持重置 autoCommit 标志，可以将 resetAutoCommit 标志设置为 false，Camel 不会尝试重置 autoCommit 标志。 当与 XA 事务一起使用时，您很可能需要将其设置为 false，以便事务管理器负责提交此 tx。")
    private boolean resetAutoCommit = true;
    @ApiModelProperty("DataSource参数Map 比如maxRows(返回行数) 或fetchSize")
    private Map<String, Object> parameters;
    @ApiModelProperty("说了你也不懂,关于JDBC3.0 和 4.0 等版本区别的,默认就行了")
    private boolean useJDBC4ColumnNameAndLabelSemantics = true;
    @ApiModelProperty("将 BLOB 列作为字节而不是字符串数据读取。这可能需要某些数据库，例如 Oracle，就必须将 BLOB 列读取为字节。")
    private boolean useGetBytesForBlob;
    @ApiModelProperty("别管了,后端会创建的")
    private NewJdbcPrepareStatementStrategy prepareStatementStrategy = new NewDefaultJdbcPrepareStatementStrategy();
    @ApiModelProperty("是否允许在查询中使用命名参数")
    private boolean allowNamedParameters = true;
    @ApiModelProperty("是否使用消息头 默认开启")
    private boolean useHeadersAsParameters = true;
    @ApiModelProperty("查询结果,默认SelectList")
    private JdbcOutputType outputType = JdbcOutputType.SelectList;
    @ApiModelProperty("输出序列化结果类,全路径名哦")
    private String outputClass;
    @ApiModelProperty("别管了,在使用 outputClass 时使用自定义的 org.apache.camel.component.jdbc.BeanRowMapper。默认实现将小写行名并跳过下划线和破折号。例如 CUST_ID 映射为 custId")
    private BeanRowMapper beanRowMapper = new DefaultBeanRowMapper();
    @ApiModelProperty("别管了,使用自定义策略来处理连接。使用 spring-jdbc 组件时不要使用自定义策略，因为默认使用特殊的 Spring ConnectionStrategy 来支持 Spring Transactions。\n")
    private ConnectionStrategy connectionStrategy = new DefaultConnectionStrategy();


}
