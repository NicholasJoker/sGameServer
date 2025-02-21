package com.fps.service;

import com.alibaba.fastjson.JSONObject;
import com.fps.utils.HttpUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;



@Service
public class LoginService {

    Logger logger = LoggerFactory.getLogger(LoginService.class);


    /**
     * @param code
     * @param type 1：微信
     * @return
     */
    public JSONObject code2sessionKey(String code, int type) {
        String stringToken = String.format(
                "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code",
                "xxxxx", "xxxxxx", code);
        if (type == 6) {
            //stringToken = String.format(
             //       "https://api.q.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code",
             //       "1109472503", "77TPaXHNJdiwWkoR", code);
            stringToken = String.format(
                   "https://api.q.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code",
                   "xxxxx", "xxxxx", code);

        }
        //抖音和头条，
        else if(type==5){
            stringToken = String.format(
                    "https://developer.toutiao.com/api/apps/jscode2session?appid=%s&secret=%s&code=%s",
                    "xxxxxxx", "xxxxx", code);
        }
       //OPPO
        else if(type==3){
            stringToken = String.format(
                    "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code",
                    "xxxxxxx", "xxxxxx", code);
        }
        //vivo
        else if(type==4){
            stringToken = String.format(
                    "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code",
                    "xxxxxx", "xxxxxxx", code);
        }
        HttpUtil httpUtil = new HttpUtil();
        return httpUtil.get(stringToken);
    }
    


}
