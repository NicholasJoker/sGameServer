package com.fps.dao;


import com.fps.entity.LoginGiftRecordEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 操作用户信息
 */
@Repository
public interface LoginGiftRecordDao {

    @Select("select * from login_gift_record where user_id = #{user_id} order by id desc limit #{limit}")
    List<LoginGiftRecordEntity> getGainedSevenGift(@Param("user_id") int user_id, @Param("limit") int limit);

    @Insert("insert into login_gift_record(user_id,days,goods_id,num,created_time)" +
            "values(#{user_id},#{days},#{goods_id},#{gold_num},now())")
    void insert(@Param("user_id") int user_id, @Param("days") int days,
                @Param("goods_id") int goods_id, @Param("gold_num") int gold_num);


    @Select("select * from login_gift_record where user_id=#{user_id} and receive_status=0 and " +
            "to_days(now())=to_days(created_time) order by id desc limit 1")
    LoginGiftRecordEntity getNoReceive(@Param("user_id") int user_id);


    @Select("select * from login_gift_record where user_id = #{user_id} and days = #{days} and  " +
            "receive_status = 0 and to_days(now()) = to_days(created_time)")
    LoginGiftRecordEntity queryTodayReward(@Param("user_id") int user_id, @Param("days") int days);


    @Update("update login_gift_record set receive_status = 1,actual_receive_num = #{receive_num} " +
            "where id =#{id}")
    void updateReceiveStatus(@Param("id") int id, @Param("receive_num") int receive_num);

}
