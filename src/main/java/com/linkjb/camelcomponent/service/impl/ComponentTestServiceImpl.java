package com.linkjb.camelcomponent.service.impl;

import com.alibaba.fastjson.JSON;
import com.linkjb.camelcomponent.common.BaseResult;
import com.linkjb.camelcomponent.dto.JdbcDTO;
import com.linkjb.camelcomponent.newjdbc.*;
import com.linkjb.camelcomponent.service.ComponentTestService;
import org.apache.camel.*;
import org.apache.camel.support.DefaultExchange;
import org.apache.camel.util.UnsafeUriCharactersEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;

/**
 * @ClassName ComponentTestServiceImpl
 * @Description 组件测试实体类
 * @Author shark
 * @Data 2022/3/18 16:57
 **/
@Service
public class ComponentTestServiceImpl implements ComponentTestService {
    @Autowired
    CamelContext camelContext;
    @Override
    public BaseResult testJdbc(JdbcDTO jdbcDTO) throws Exception {
        Component newJdbc = camelContext.getComponent("sharkjdbc");
        //generateDto(jdbcDTO);
        NewJdbcEndpoint endpoint = (NewJdbcEndpoint)newJdbc.createEndpoint("sharkJdbc:" + JSON.toJSONString(jdbcDTO));
        NewJdbcProducer producer = (NewJdbcProducer)endpoint.createProducer();
        Exchange exchange = new DefaultExchange(camelContext);
        producer.process(exchange);
        return BaseResult.ok(exchange.getMessage().getBody().toString());
    }

    public void generateDto(JdbcDTO jdbcDTO){
        NewDefaultJdbcPrepareStatementStrategy newDefaultJdbcPrepareStatementStrategy = new NewDefaultJdbcPrepareStatementStrategy();
        BeanRowMapper beanRowMapper = new DefaultBeanRowMapper();
        ConnectionStrategy connectionStrategy = new DefaultConnectionStrategy();
        JdbcOutputType outputType = JdbcOutputType.SelectList;
        jdbcDTO.setPrepareStatementStrategy(newDefaultJdbcPrepareStatementStrategy);
        jdbcDTO.setBeanRowMapper(beanRowMapper);
        jdbcDTO.setConnectionStrategy(connectionStrategy);
        jdbcDTO.setOutputType(outputType);
        if(jdbcDTO.getChild()!=null){
            for(JdbcDTO jdbcDTO1:jdbcDTO.getChild()){
                generateDto(jdbcDTO1);
            }
        }
    }
}
