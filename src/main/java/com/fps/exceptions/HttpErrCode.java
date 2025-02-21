package com.fps.exceptions;


public enum HttpErrCode {

    Consume_Err(510, "无消耗物品"),
    Prop_Deficiency(511, "道具数量不足"),
    No_Debris(512, "碎片ID不存在"),
    Type_Err(513, "合成券的碎片种类不正确"),
    Debris_Deficiency(514, "碎片数量不足"),
    User_Not_Exist(515, "用户不存在"),
    No_Coupon(516, "无此优惠券"),
    Uid_Not_Exist(517, "uid不存在"),

    Unselected_Debris(518, "未选择碎片"),

    No_Repeat(519, "已经领取过了，不可重复领取"),

    Not_Empty(520, "增加的物品不能为空"),

    Wrong_Operation(521, "错误操作");

    private int index;

    private int name;

    private String descript;


    private HttpErrCode(int _name, String _desc) {
        index = this.ordinal();
        name = _name;
        descript = _desc;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }
}
