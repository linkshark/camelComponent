package com.linkjb.camelcomponent.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Map;

@Data
@Accessors(chain = true)
@ApiModel("组件节点信息")
public class MockNode implements Serializable {
    private static final Long serialVersionUID = -2374237237472938L;
    @ApiModelProperty("组件ID")
    private String id;
    @ApiModelProperty("组件名称")
    private String name;
    @ApiModelProperty("组件属性")
    private Map<String, Object> properties;
    @ApiModelProperty(value = "组件类型", example = "NODE")
    private Integer type;
    @ApiModelProperty(value = "组件子属性", example = "groovy")
    private Integer subType;

}
