package com.linkjb.camelcomponent.camel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @ClassName MyOrderService
 * @Description TODO
 * @Author shark
 * @Data 2022/4/29 22:29
 **/
@Slf4j
@Component
public class MyOrderService {

    private static int counter;

    /**
     * We just handle the order by returning an id line for the order
     */
    public String handleOrder(String line) {
        log.info("HandleOrder: " + line);
        return "(id=" + ++counter + ",item=" + line + ")";
    }

    /**
     * We use the same bean for building the combined response to send
     * back to the original caller
     */
    public String buildCombinedResponse(String line) {
        log.info("BuildCombinedResponse: " + line);
        return "Response[" + line + "]";
    }
}
