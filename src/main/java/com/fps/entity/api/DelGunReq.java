package com.fps.entity.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ApiModel
@ToString(callSuper = true)
public class DelGunReq extends RequestApi {

    @ApiModelProperty("需要删除的枪ID")
    int removeGunId;

}
