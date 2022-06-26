package com.linkjb.camelcomponent.mock;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Endpoint;
import org.apache.camel.support.DefaultComponent;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @ClassName MockComponent
 * @Description TODO
 * @Author shark
 * @Data 2021/12/16 17:58
 **/
@Component("hcsb-mock")
@Slf4j
public class MockComponent extends DefaultComponent {


    @Override
    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        MockEndpoint mockEndpoint = createMockEndpoint(uri, remaining, parameters);
        log.info("创建endPoint ing ");
        return mockEndpoint;
    }

    protected MockEndpoint createMockEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        log.info("创建endPoint ing ");
        return new MockEndpoint(this, uri, remaining);
    }


}
