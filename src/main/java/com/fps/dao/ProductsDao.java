package com.fps.dao;

import com.fps.entity.ProductsEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductsDao {

    @Insert("insert into products(id,goods_id,name,price,created_time)" +
            "values(#{id},#{goods_id},#{name},#{price},#{created_time})")
    void save(@Param("entity") ProductsEntity entity);


    //查询指定商品的信息
    @Select("select * from products where goods_id = #{goods_id} limit 1")
    ProductsEntity queryByGoodsId(@Param("goods_id") int goods_id);


    @Select("select * from products where #{gold} - price >=0 order by id desc limit 1")
    ProductsEntity getMaxGun(@Param("gold") double gold);


}
