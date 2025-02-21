package com.fps.entity.api;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel
public class UserPack {

    @ApiModelProperty("用户当前的金币")
    private double gold;
    @ApiModelProperty("当前解锁到的章节")
    private int unlockChapter;
    @ApiModelProperty("当前解锁到的章节的关卡")
    private int unlockLevel;
    @ApiModelProperty("用户当前选择使用的枪id")
    private int selectGunId;
    @ApiModelProperty("用户空投箱中当前存有的枪的数量")
    private int kongtouGunNum;
    @ApiModelProperty("用户当前拥有的枪，如{“20001”:2,”20002”:1,”20003:1}")
    private JSONObject gunPackage;
    @ApiModelProperty("用户购买过的枪记录，如 {“20001”:1,”20003”2} 解释：1号枪购买过一次，3号枪购买过2次")
    private JSONObject buyedGunInfo;

}
