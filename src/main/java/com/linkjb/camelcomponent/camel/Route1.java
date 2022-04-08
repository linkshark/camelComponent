package com.linkjb.camelcomponent.camel;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * @ClassName Route1
 * @Description 路由1
 * @Author shark
 * @Data 2022/3/18 16:58
 **/
@Component
public class Route1 extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("jetty:http://0.0.0.0:1234/go").id("test1")
                .autoStartup(true).convertBodyTo(String.class, "UTF-8").setBody(constant("测试成功"))
                .to("log:ss");
    }
}
