package com.fps.dao;

import com.fps.entity.UsersEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersDao {

    @Select("select * from users where id = #{id}")
    UsersEntity getById(@Param("id") int id);

    @Select("select * from users where openid = #{openid} limit 1")
    UsersEntity getByOpenid(@Param("openid") String openid);


    @Update("update users set default_gun = #{gun_id} where id = #{id}")
    int changeDefaultGun(@Param("gun_id") int gun_id, @Param("id") int id);

    @Select("select * from users where openid = #{openid} or youke_openid=#{openid} limit 1")
    UsersEntity queryByOpenid(@Param("openid") String openid);


    @Select("select * from users where youke_openid =#{youkeOpenid} limit 1")
    UsersEntity queryByYoukeOpenid(@Param("youkeOpenid") String youkeOpenid);

    @Update("update users set openid = #{openid},platform_type = #{platform_type},username = #{username}," +
            "head_img = #{head_img} where id = #{id}")
    void relationOpenid(@Param("openid") String openid, @Param("platform_type") int platform_type,
                        @Param("username") String username,@Param("head_img") String head_img,
                        @Param("id") int id);


    @Update("update users set default_gun = #{init_gun} where id = #{id} and default_gun=#{del_gun}")
    void setDefaultGun(@Param("init_gun") int init_gun, @Param("id") int id, @Param("del_gun") int del_gun);


    @Update("update users set level = #{level},chapter = #{chapter} where id = #{id}")
    void updateLevelChapter(@Param("level") int level, @Param("chapter") int chapter, @Param("id") int id);


    @Insert("insert into users(username,head_img,openid,youke_openid,default_gun,platform_type,login_time,created_time)" +
            "values(#{username},#{head_img},#{openid},#{youkeOpenid},#{default_gun},#{platform_type}," +
            "date_sub(now(),interval 1 day),now())")
    @Options(useGeneratedKeys = true, keyProperty = "entity.id")
    void insert(@Param("username") String username, @Param("head_img") String head_img,
                @Param("openid") String openid, @Param("youkeOpenid") String youkeOpenid, @Param("default_gun") int default_gun,
                @Param("platform_type") int platform_type, @Param("entity") UsersEntity entity);


    @Update("update users set login_days = 0 where id = #{id} and " +
            "date_format(login_time,'%Y-%m-%d')<date_format(now(),'%Y-%m-%d') and login_days=8")
    void updateLoginDaysToZero(@Param("id") int id);


    @Update("update users set login_days = login_days+1,login_time=now() where id = #{id} and " +
            "date_format(login_time,'%Y-%m-%d')<date_format(now(),'%Y-%m-%d') and login_days <8")
    int updateLoginDays(@Param("id") int id);


    @Update("update users set openid = #{openid} where youke_openid = #{youkeOpenid} and (openid is null or openid='')")
    int updateOpenid(@Param("openid") String openid, @Param("youkeOpenid") String youkeOpenid);


    @Update("update user_pack set num = #{num},updated_time = now() where user_id = #{user_id} " +
            "and goods_id = #{goods_id}")
    int syncGoldNum(@Param("num") double num, @Param("user_id") int user_id, @Param("goods_id") int goods_id);


    @Update("update users set kongtou_gun_num = #{kongtou_gun_num} where id = #{id}")
    void updateKongtouGunNum(@Param("kongtou_gun_num") int kongtou_gun_num, @Param("id") int id);


    @Select("select * from users where platform_type = #{platform_type} order by level desc limit 50")
    List<UsersEntity> getRankingList(@Param("platform_type") int platform_type);


    @Update("update users set kongtou_gun_num = kongtou_gun_num-1 where id = #{id} and kongtou_gun_num-1 >= 0")
    int reduceKongtouGunNum(@Param("id") int id);


    //更新当前教程步骤
    @Update("update users set guide = #{guide} where id = #{id}")
    void updateGuide(@Param("guide") String guide, @Param("id") int id);



    @Update("update users a set a.offline_collect_status = 0 " +
            "where (" +
            "select (unix_timestamp(now())-unix_timestamp((select updated_time from user_pack where goods_id = 10001 and user_id = a.id)))" +
            ")>#{gold_sync_time}")
    int updateNotClaimed(@Param("gold_sync_time") int gold_sync_time);


    @Select("select unix_timestamp(now())-UNIX_TIMESTAMP(updated_time) - #{gold_sync_time} " +
            "from user_pack where user_id = #{id} and goods_id = 10001")
    int getOfflineTime(@Param("id") int id, @Param("gold_sync_time") int gold_sync_time);


    //更新离线领取状态码
    @Update("update users set offline_collect_status = 1 where id = #{id}")
    void updateOfflineCollectStatus(@Param("id") int id);

}
