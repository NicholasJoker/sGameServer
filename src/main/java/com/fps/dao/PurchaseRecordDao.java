package com.fps.dao;

import com.fps.entity.ProductsEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


@Repository
public interface PurchaseRecordDao {

    @Insert("insert into purchase_record(user_id,product_id,goods_id,actual_price,created_time)" +
            "values(#{user_id},#{product_id},#{goods_id},#{actual_price},now())")
    void save(@Param("user_id") int user_id, @Param("product_id") int product_id,
              @Param("goods_id") int goods_id, @Param("actual_price") double actual_price);


    @Select("select count(*) from purchase_record where user_id = #{user_id} and goods_id = #{goods_id}")
    int countBuyTimesGoodsId(@Param("user_id") int user_id, @Param("goods_id") int goods_id);


}
