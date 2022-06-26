package com.linkjb.camelcomponent.mock;


import org.apache.camel.Category;
import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.spi.Metadata;
import org.apache.camel.spi.UriEndpoint;
import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriPath;
import org.apache.camel.support.DefaultEndpoint;
import org.apache.camel.util.StringHelper;


/**
 * @ClassName DataTransEndpoint
 * @Description 数据转换
 * @Author shark
 * @Data 2021/12/16 17:59
 **/
@UriEndpoint(firstVersion = "1.0.0", scheme = "trans", title = "Trans", syntax = "trans:name",
        category = {Category.CORE, Category.ENDPOINT})
public class MockEndpoint extends DefaultEndpoint {
    private final MockComponent component;
    private final String key;
    public String scriptName;
    @UriPath(description = "method")
    @Metadata(required = true)
    private String method;
    @UriPath(description = "parameter")
    @Metadata(required = true)
    private String parameter;
    @UriParam(
            label = "advanced"
    )
    private boolean synchronous;


    public MockEndpoint(MockComponent component, String uri, String scriptName) {
        super(uri, component);
        this.component = component;
        this.scriptName = scriptName;
        if (uri.indexOf('?') != -1) {
            this.key = StringHelper.before(uri, "?");
            String parameter = StringHelper.after(uri, "?");
            String[] parameters = parameter.split("&");
            for (String a : parameters) {
                if (a.contains("method=")) {
                    this.method = a;
                }
            }
        } else {
            this.key = uri;
        }
    }

    public boolean isSynchronous() {
        return synchronous;
    }

    public void setSynchronous(boolean synchronous) {
        this.synchronous = synchronous;
    }

    @Override
    public MockComponent getComponent() {
        return component;
    }

    public String getScriptName() {
        return scriptName;
    }

    public void setScriptName(String scriptName) {
        this.scriptName = scriptName;
    }

    public String getKey() {
        return key;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    @Override
    public Producer createProducer() throws Exception {
        System.out.println("===============================================进入createProducer");
        return new MockProducer(this, key, scriptName);
    }

    @Override
    public Consumer createConsumer(Processor processor) throws Exception {
        throw new UnsupportedOperationException("Not supported");
    }

}
