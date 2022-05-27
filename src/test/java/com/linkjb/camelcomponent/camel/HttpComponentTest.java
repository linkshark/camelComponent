package com.linkjb.camelcomponent.camel;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Producer;
import org.apache.camel.component.http.HttpComponent;
import org.apache.camel.component.http.HttpEndpoint;
import org.apache.camel.support.DefaultExchange;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @ClassName HttpComponentTest
 * @Description TODO
 * @Author shark
 * @Data 2022/5/25 17:20
 **/

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class HttpComponentTest {
    @Autowired
    CamelContext camelContext;

    @Test
    public void httpTest() throws Exception {
        Exchange exchange = new DefaultExchange(camelContext);
        HttpComponent http = (HttpComponent) camelContext.getComponent("http");
        exchange.getMessage().setBody("1");
        HttpEndpoint endpoint = (HttpEndpoint) http.createEndpoint("http://127.0.0.1:7777/testHttp?bridgeEndpoint=true");
        Producer producer = endpoint.createProducer();
        producer.start();
        producer.process(exchange);
        producer.close();
        //http.createProducer(camelContext,)
        log.info("ss");
    }

}
