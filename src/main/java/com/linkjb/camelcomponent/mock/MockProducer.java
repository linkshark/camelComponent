package com.linkjb.camelcomponent.mock;


import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.support.DefaultProducer;

/**
 * @ClassName DataTransProducer
 * @Description TODO
 * @Author shark
 * @Data 2021/12/17 08:58
 **/
@Slf4j
public class MockProducer extends DefaultProducer {

    private final MockEndpoint endpoint;
    private final MockComponent component;
    private final String key;
    private String parameter;
    private String scriptName;


    public MockProducer(MockEndpoint endpoint, String key, String scriptName) {
        super(endpoint);
        parameter = endpoint.getEndpointUri().substring(endpoint.getEndpointUri().indexOf(":") + 1);
        this.endpoint = endpoint;
        this.component = endpoint.getComponent();
        this.key = key;
        this.scriptName = scriptName;
    }
//    public DataTransProducer(DataTransEndpoint endpoint, String key,String scriptName) {
//        super(endpoint);
//        System.out.println("===============================================进入testProducer");
//        parameter = endpoint.getEndpointUri().substring(endpoint.getEndpointUri().indexOf(":") + 1);
//        this.endpoint = endpoint;
//        this.component = endpoint.getComponent();
//        this.key = key;
//        this.scriptName = scriptName;
//    }

    @Override
    public MockEndpoint getEndpoint() {
        return (MockEndpoint) super.getEndpoint();
    }


    @Override
    public void process(Exchange exchange) throws Exception {
        log.info("需要执行脚本名称为: " + scriptName + "的脚本");
        String body = exchange.getIn().getBody(String.class);
        log.info(exchange.getMessage().getBody(String.class));
        log.info(exchange.getMessage().getBody(String.class));
        //Result doexecute = transHelper.doexecute(scriptName, body);
        //exchange.getMessage().setBody(doexecute.getMessage());

    }

}
