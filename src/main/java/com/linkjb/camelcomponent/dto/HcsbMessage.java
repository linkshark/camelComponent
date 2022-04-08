package com.linkjb.camelcomponent.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @ClassName HcsbMessage
 * @Description 路由消息扭转类
 * @Author shark
 * @Data 2022/3/18 16:22
 **/
@Data
@Accessors(chain = true)
@ApiModel(value = "路由消息流转类")
public class HcsbMessage implements Serializable {
    private static final Long serialVersionUID = -237423467237472938L;
    @ApiModelProperty(value = "历史消息头")
    private String historyHeader;
    @ApiModelProperty(value = "历史消息体")
    private Object historyBody;
    @ApiModelProperty(value = "消息头")
    private String header;
    @ApiModelProperty(value = "消息体")
    private Object Body;
}
