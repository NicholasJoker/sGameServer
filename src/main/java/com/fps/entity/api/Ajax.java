package com.fps.entity.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel
@Data
public class Ajax<T> implements Serializable {

    @ApiModelProperty("状态码，成功为0， 其他错误")
    private int code;

    @ApiModelProperty("错误消息，仅在code不为0时出现")
    private String errMsg;

    @ApiModelProperty(value = "成功内容体，仅在code为0时出现")
    private T data;


    //客户端回调标识
    private String digest;

    public Ajax() {
        this.code = 0;
        this.data = null;
    }

    public Ajax(T data) {
        this.code = 0;
        this.data = data;
    }

    public Ajax(int code, String errMsg) {
        this.code = code;
        this.errMsg = errMsg;
    }

}