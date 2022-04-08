package com.linkjb.camelcomponent.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName NodeMockDTO
 * @Description 节点信息DTO
 * @Author shark
 * @Data 2022/3/18 16:27
 **/
@Data
@Accessors(chain = true)
@ApiModel("组件测试DTO")
public class NodeMockDTO {
    private static final Long serialVersionUID = -2374234672938L;
    @ApiModelProperty("前置groovy脚本")
    private String groovyScript;
    @ApiModelProperty("路由消息流转类")
    private HcsbMessage hcsbMessage;
    @ApiModelProperty("组件节点信息")
    private MockNode mockNode;


}
