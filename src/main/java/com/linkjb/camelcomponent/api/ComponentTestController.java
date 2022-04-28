package com.linkjb.camelcomponent.api;

import com.linkjb.camelcomponent.common.BaseResult;
import com.linkjb.camelcomponent.dto.JdbcDTO;
import com.linkjb.camelcomponent.dto.NodeMockDTO;
import com.linkjb.camelcomponent.service.ComponentTestService;
import io.swagger.annotations.Api;
import org.apache.camel.CamelContext;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName ComponentTestController
 * @Description 组件测试controller
 * @Author shark
 * @Data 2022/3/18 16:10
 **/
@RestController
@RequestMapping(value = "/componentTest")
@Api("组件测试controller")
public class ComponentTestController {
    final CamelContext camelContext;

    final ComponentTestService componentTestService;

    public ComponentTestController(CamelContext camelContext , ComponentTestService componentTestService) {
        this.camelContext = camelContext;
        this.componentTestService = componentTestService;
    }

    @GetMapping("/hello")
    public BaseResult test() {
        return BaseResult.ok("hello-camel");
    }

    @GetMapping("/stopTest")
    public BaseResult mock() throws Exception {
        camelContext.getRouteController().stopRoute("test1");
        boolean test1 = camelContext.removeRoute("test1");
        return BaseResult.ok("hello-camel");
    }


    @PostMapping("/testCom")
    public BaseResult mock(@RequestBody NodeMockDTO nodeMockDTO) throws Exception {
        camelContext.getRouteController().stopRoute("test1");
        boolean test1 = camelContext.removeRoute("test1");
        return BaseResult.ok("hello-camel");
    }

    @PostMapping("/jdbc")
    public BaseResult testJdbc(@RequestBody JdbcDTO jdbcDTO) throws Exception {
        return componentTestService.testJdbc(jdbcDTO);
    }


}
