package com.fps.dao;

import com.fps.entity.api.ShareVideoCfg;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ShareStrategyDao {

    //根据类型查询分享限制（客户端用）
    @Select("select id,title,max_share maxShare,max_video maxVideo,priority from share_strategy where type = #{type}")
    List<ShareVideoCfg> getShareVideoCfgByType(@Param("type") int type);

}
