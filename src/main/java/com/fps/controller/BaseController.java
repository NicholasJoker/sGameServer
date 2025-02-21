package com.fps.controller;

import com.fps.dao.UsersDao;
import com.fps.entity.UsersEntity;
import com.fps.entity.api.Ajax;
import com.fps.exceptions.HttpStatusException;
import com.fps.utils.PublicUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

public abstract class BaseController implements Serializable {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    UsersDao usersDao;
    @ExceptionHandler(Exception.class)
    public Ajax handleException(Exception ex) {
        logger.error("handleException", ex);
        return new Ajax(-1, ex.getMessage());
    }
    protected UsersEntity getUserInfo(String openid, HttpServletResponse response) throws Exception {
        if (PublicUtil.isEmpty(openid)) {
            HttpStatusException.httpStatusEx(403, "无效openid", response);
            logger.warn("用户openid不能为空");
            return null;
        }
        UsersEntity userInfo = usersDao.queryByOpenid(openid);
        if (PublicUtil.isEmpty(userInfo)) {
            logger.info("无效openid：{}", openid);
            HttpStatusException.httpStatusEx(403, "无效openid", response);
            return null;
        }
        return userInfo;
    }
}