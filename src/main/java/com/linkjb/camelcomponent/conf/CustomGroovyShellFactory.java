package com.linkjb.camelcomponent.conf;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.codehaus.groovy.control.customizers.ImportCustomizer;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @ClassName CustomGroovyShellFactory
 * @Description groovyShell工厂
 * @Author shark
 * @Data 2022/4/20 10:05
 **/
@Component
@Slf4j
public class CustomGroovyShellFactory implements ApplicationContextAware {
    private ApplicationContext applicationContext;


    @Bean
    public GroovyShell createGroovyShell() {
        Binding binding = new Binding();
        Map<String, Object> beanMap = applicationContext.getBeansOfType(Object.class);
        //遍历设置所有bean,可以根据需求在循环中对bean做过滤
        for (String beanName : beanMap.keySet()) {
            //log.info(beanName);
            binding.setVariable(beanName, beanMap.get(beanName));
            //binding.setVariable("redisUtils","");
        }


        ImportCustomizer importCustomizer = new ImportCustomizer();
        importCustomizer.addStarImports("groovy");
        importCustomizer.addStarImports("com.alibaba.fastjson");
        CompilerConfiguration configuration = new CompilerConfiguration();
        configuration.addCompilationCustomizers(importCustomizer);
        configuration.setDebug(true);
        return new GroovyShell(binding, configuration);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;

    }
}
