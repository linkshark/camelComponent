package com.linkjb.camelcomponent.camel;


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
    public void configure() {
//        from("jetty:http://0.0.0.0:8848/go").id("test1")
//                .autoStartup(true).convertBodyTo(String.class, "UTF-8").setBody(constant("select * from test"))
//                .to("jdbc:datasource")
//                .to("log:ss");
//
//        from("jetty:http://0.0.0.0:8848/gocount").id("test2")
//                .autoStartup(true).convertBodyTo(String.class, "UTF-8").setBody(constant("select count(*) from test"))
//                .to("jdbc:datasource")
//                .to("log:ss");
//
//        from("jetty:http://0.0.0.0:8848/random").id("test3")
//                .autoStartup(true).convertBodyTo(String.class, "UTF-8")
//                .to("jdbc:datasource?useHeadersAsParameters=true&statement.maxRows=10&statement.fetchSize=10")
//                .to("log:ss");


        from("jetty:http://0.0.0.0:8888/s").id("selfRoute")
                .autoStartup(true).convertBodyTo(String.class, "UTF-8")
                .to("sharkjdbc:{\"queryName\": \"father\",\"dataSourceId\": \"datasource\",\"sql\": \"select * from test where 1=1\",\"child\": [{\"queryName\": \"son\",\"dataSourceId\": \"datasource\",\"sql\": \"select * from grade where name = ${parent.name}\",\"child\": [{\"queryName\": \"grandSon\",\"dataSourceId\": \"datasource\",\"sql\": \"select * from grade where name = ${parent.name}\"}]}]}")
                .to("log:ss");
    }
}
