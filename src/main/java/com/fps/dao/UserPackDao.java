package com.fps.dao;

import com.alibaba.fastjson.JSONObject;
import com.fps.entity.UserPackEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPackDao {

    @Select("select count(id) from user_pack where user_id = #{user_id} and goods_id = #{goods_id}")
    int checkIsHaveGoods(@Param("user_id") int user_id, @Param("goods_id") int goods_id);


    @Update("update user_pack set num = num - 1 where user_id = #{user_id} and goods_id = #{goods_id} " +
            "and num - 1>=0")
    int delGoodsByUserId(@Param("user_id") int user_id, @Param("goods_id") int goods_id);


    @Update("update user_pack set num = num + #{num} where user_id = #{user_id} " +
            "and goods_id = #{goods_id} and num + #{num}>=0")
    int updateNumByUserID(@Param("num") double num, @Param("user_id") int user_id, @Param("goods_id") int goods_id);

    @Select("select goods_id,num from user_pack where user_id = #{user_id} and num <> 0 " +
            "or (goods_id=10001 and goods_id=10002)")
    List<JSONObject> queryPackByUserId(@Param("user_id") int user_id);


    @Select("select num from user_pack where user_id = #{user_id} and goods_id = #{goods_id}")
    Double getGoodsNum(@Param("user_id") int user_id, @Param("goods_id") int goods_id);


    @Select("select goods_id,num from user_pack where user_id = #{user_id} and goods_id = #{goods_id}")
    JSONObject getUserPackByGoodsId(@Param("user_id") int user_id, @Param("goods_id") int goods_id);


    @Insert("insert into user_pack(user_id,goods_id,num,created_time,updated_time)" +
            "values(#{user_id},#{goods_id},#{num},now(),now())")
    void insert(@Param("user_id") int user_id, @Param("goods_id") int goods_id,
                @Param("num") int num);


    @Select("select * from user_pack where user_id = #{user_id} and goods_id = #{goods_id} limit 1")
    UserPackEntity getUserPackGoods(@Param("user_id") int user_id, @Param("goods_id") int goods_id);


    @Select("select sum(num) from user_pack where user_id = #{user_id} and num <> 0 " +
            "and goods_id<>10001 and goods_id<>10002")
    int countGunTotal(@Param("user_id") int user_id);


    @Select("select goods_id,count(*)num from purchase_record where user_id = #{user_id} group by goods_id")
    List<JSONObject> getPurchaseRecords(@Param("user_id") int user_id);


    @Update("update user_pack set updated_time = now() where user_id = #{user_id} and goods_id = 10001")
    void updateGoldUpdatedTime(@Param("user_id") int user_id);

}
