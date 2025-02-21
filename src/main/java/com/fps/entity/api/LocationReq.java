package com.fps.entity.api;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class LocationReq {
    @ApiModelProperty("key")
    public String key;
    @ApiModelProperty("经度")
    public String log;
    @ApiModelProperty("纬度")
    public String lat;
}
