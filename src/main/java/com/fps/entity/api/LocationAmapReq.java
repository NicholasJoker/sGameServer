package com.fps.entity.api;

import io.swagger.annotations.ApiModelProperty;

public class LocationAmapReq {
        @ApiModelProperty("amapKey")
        public String key;
        @ApiModelProperty("位置")
        public String location;
}
