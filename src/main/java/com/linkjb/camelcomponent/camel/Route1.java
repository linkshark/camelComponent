package com.linkjb.camelcomponent.camel;


import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName Route1
 * @Description 路由1
 * @Author shark
 * @Data 2022/3/18 16:58
 **/
@Component
public class Route1 extends RouteBuilder {
    @Autowired
    CamelContext camelContext;
    @Override
    public void configure() {

        from("jetty:http://0.0.0.0:8848/go").id("test1")
                .autoStartup(true).convertBodyTo(String.class, "UTF-8").setBody(constant("select * from test"))
                .to("jdbc:datasource")
                .to("log:ss");
//
//        from("jetty:http://0.0.0.0:8848/gocount").id("test2")
//                .autoStartup(true).convertBodyTo(String.class, "UTF-8").setBody(constant("select count(*) from test"))
//                .to("jdbc:datasource")
//                .to("log:ss");'
//
        from("jetty:http://0.0.0.0:8848/random").id("test3")
                .autoStartup(true).convertBodyTo(String.class, "UTF-8")
                .setHeader("name", constant("小A"))
                .setHeader("id", constant("1"))
                .setBody(constant("\"select * from test where name = :?name and id = :?id order by id\""))
                .to("jdbc:datasource?transacted=true&statement.maxRows=1&useHeadersAsParameters=true")
                //useHeadersAsParameters=true
                .to("log:ss");


        from("jetty:http://0.0.0.0:8888/s").id("selfRoute")
                .autoStartup(true).convertBodyTo(String.class, "UTF-8")
                .to("sharkjdbc:{\"queryName\": \"father\",\"dataSourceId\": \"datasource\",\"sql\": \"select * from test where id = ${body}\",\"child\": [{\"queryName\": \"son\",\"dataSourceId\": \"datasource\",\"sql\": \"select * from grade where name = ${parent.name}\",\"child\": [{\"queryName\": \"grandSon\",\"dataSourceId\": \"datasource\",\"sql\": \"select * from grade where name = ${parent.name}\"}]}]}")
                .to("log:ss");

        from("jetty:http://0.0.0.0:7777/testHttp")
                .autoStartup(true)
                .convertBodyTo(String.class, "UTF-8")
                .process(exchange ->
                        log.info(exchange.getMessage().getBody().toString()));


        from("jetty:http://0.0.0.0:8888/split").id("splitRoute")
                .autoStartup(true).convertBodyTo(String.class, "UTF-8")
                .split(constant("1,2,3,4").tokenize(",")).to("bean:myOrderService?method=buildCombinedResponse");
    }
}
