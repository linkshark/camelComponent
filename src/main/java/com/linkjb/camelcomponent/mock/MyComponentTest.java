package com.linkjb.camelcomponent.mock;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * @ClassName MyComponentTest
 * @Description TODO
 * @Author shark
 * @Data 2021/12/17 13:48
 **/
@Slf4j
public class MyComponentTest {
    public static void main(String[] args) throws Exception {
        CamelContext camelContext = new DefaultCamelContext();
        //为了简单起见，用该方法替代了在类路径META-INF/services/org/apache/camel/component/下放置properties文件方式
        camelContext.addComponent("trans", new MockComponent());

        camelContext.addRoutes(new RouteBuilder() {

            @Override
            public void configure() throws Exception {
                this.from("direct:register").autoStartup(false).process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        log.info("进入自研组件之前" + exchange.getMessage().getBody(String.class));
                    }
                }).to("trans:ss").process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        log.info("进入自研组件之后" + exchange.getMessage().getBody(String.class));
                    }
                });

            }
        });

        camelContext.start();
        ProducerTemplate template = camelContext.createProducerTemplate();
        //template.sendBodyAndHeader("test:register", "parameter=good111", "tagFlag", 1);


        template.sendBodyAndHeader("direct:register", "你好呀", "tagFlag", 1);
        Object object = new Object();
        synchronized (object) {
            object.wait();
        }
    }
}
