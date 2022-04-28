package com.linkjb.camelcomponent.enums.init;

import com.linkjb.camelcomponent.jdbc.JdbcComponent;
import com.linkjb.camelcomponent.newjdbc.NewJdbcComponent;
import groovy.util.logging.Slf4j;
import org.apache.camel.CamelContext;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @ClassName InitComliner
 * @Description 初始化类-注册自定义组件
 * @Author shark
 * @Data 2022/4/21 10:24
 **/
@Slf4j
@Component
public class InitComliner implements InitializingBean {
    final CamelContext camelContext;

    public InitComliner(CamelContext camelContext) {
        this.camelContext = camelContext;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        camelContext.addComponent("jdbc", new JdbcComponent());
        camelContext.addComponent("sharkjdbc", new NewJdbcComponent());
        camelContext.setAutoStartup(true);
    }
}
