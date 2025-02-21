package com.fps.service;

import cn.hutool.system.UserInfo;
import com.alibaba.fastjson.JSONObject;
import com.fps.dao.LoginGiftRecordDao;
import com.fps.dao.UserPackDao;
import com.fps.dao.UsersDao;
import com.fps.entity.LoginGiftRecordEntity;
import com.fps.entity.UsersEntity;
import com.fps.entity.api.*;
import com.fps.utils.PublicUtil;
import io.swagger.annotations.ApiModelProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService {
    Logger logger = LoggerFactory.getLogger(UserService.class);


    @Autowired
    UsersDao usersDao;
    @Autowired
    UserPackDao userPackDao;
    @Autowired
    LoginGiftRecordDao loginGiftRecordDao;


    @Value("${login.gift.pack}")
    String loginGiftPack;


    /**
     * 查询用户购买记录
     *
     * @param user_id 用户ID
     */
    public JSONObject getPurchaseRecord(int user_id) {
        JSONObject js = new JSONObject();
        List<JSONObject> purchaseRecords = userPackDao.getPurchaseRecords(user_id);
        purchaseRecords.forEach(item -> {
            js.put(item.getString("goods_id"), item.getIntValue("num"));
        });
        return js;
    }


    /**
     * 获取用户背包信息
     *
     * @param id 用户ID
     * @return
     */
    public UserPack getUserPack(int id) {
        UsersEntity userInfo = usersDao.getById(id);
        UserPack userPack = new UserPack();
        userPack.setGold(getGoldNum(userInfo.getId()));
        userPack.setUnlockChapter(userInfo.getChapter());
        userPack.setUnlockLevel(userInfo.getLevel());
        userPack.setSelectGunId(userInfo.getDefault_gun());
        userPack.setKongtouGunNum(userInfo.getKongtou_gun_num());
        userPack.setGunPackage(getChangeGoodsInfo(userInfo.getId(), Constant.Gun_Prefix));
        userPack.setBuyedGunInfo(getPurchaseRecord(id));
        return userPack;
    }


    public Double getGoldNum(int user_id) {
        Double goodsNum = userPackDao.getGoodsNum(user_id, Constant.Gold);
        return goodsNum == null ? 0 : goodsNum;
    }



    public JSONObject getChangeGoodsInfo(int user_id, int type) {
        UsersEntity usersEntity = usersDao.getById(user_id);

        List<JSONObject> packs = userPackDao.queryPackByUserId(user_id);

        JSONObject pack = new JSONObject();

        switch (type) {
            case Constant.Currency_Prefix:
                packs.forEach(item -> {
                    if (item.getInteger("goods_id") / 10000 == Constant.Currency_Prefix) {
                        pack.put(item.getString("goods_id"), item.getDoubleValue("num"));
                    }
                });
                break;
            case Constant.Gun_Prefix:
                packs.forEach(item -> {
                    //2开头的是枪
                    if (item.getInteger("goods_id") / 10000 == Constant.Gun_Prefix) {
                        pack.put(item.getString("goods_id"), item.getIntValue("num"));
                    }
                });
                break;
            case Constant.Select_Gun_Id:
                pack.put("selectGunId", usersEntity.getDefault_gun());
                break;
            case Constant.Unlock_Chapter_Level:
                pack.put("unlockLevel", usersEntity.getLevel());
                pack.put("unlockChapter", usersEntity.getChapter());
                break;
            case Constant.Gold:
                pack.put("gold", userPackDao.getUserPackByGoodsId(user_id, Constant.Gold).getDoubleValue("num"));
                break;
        }

        return pack;
    }



    public JSONObject getUserSingleGoods(int id, int goods_id) {
        UsersEntity userInfo = usersDao.getById(id);

        JSONObject jsonObject = userPackDao.getUserPackByGoodsId(userInfo.getId(), goods_id);

        JSONObject pack = new JSONObject();
        pack.put(jsonObject.getString("goods_id"), jsonObject.getString("num"));

        return pack;
    }


    /**
     * 获取7天登录礼包
     *
     * @param userInfo 用户信息
     * @return
     */
    public LoginGiftPack getSevenDayGiftPack(UsersEntity userInfo) {

        //userInfo = usersDao.getById(userInfo.getId());
        usersDao.updateLoginDaysToZero(userInfo.getId());
        userInfo = usersDao.getById(userInfo.getId());
        int i = usersDao.updateLoginDays(userInfo.getId());
        userInfo = usersDao.getById(userInfo.getId());

        int id = userInfo.getId();

        int login_days = userInfo.getLogin_days();

        JSONObject login_gift_pack = JSONObject.parseObject(loginGiftPack);

        if (i > 0) {
            int gold_num = login_gift_pack.getJSONObject(login_days + "").getIntValue("" + Constant.Gold);
            loginGiftRecordDao.insert(id, login_days, Constant.Gold, gold_num);
        }

        /**
         * 查询用户当日登录奖励是否领取
         */
        boolean todayIsGet = true;
        LoginGiftRecordEntity noReceive = loginGiftRecordDao.getNoReceive(id);
        if (PublicUtil.isNotEmpty(noReceive)) todayIsGet = false;


        int before_day = 0;

        List giftPacks = new ArrayList();

        if (login_days < 8) {
            List<LoginGiftRecordEntity> gainedSevenGifts = loginGiftRecordDao.getGainedSevenGift(id, login_days);
            if (PublicUtil.isNotEmpty(gainedSevenGifts)) {

                before_day = gainedSevenGifts.size();

                //封装今天之前的登录奖励给客户端
                for (LoginGiftRecordEntity gainedSevenGift : gainedSevenGifts) {
                    GiftPack giftPack = new GiftPack();
                    giftPack.setKey(gainedSevenGift.getGoods_id() + "");
                    giftPack.setNum(gainedSevenGift.getNum());
                    giftPacks.add(giftPack);
                }
            }


            //查询7天登录 今天之后的剩余天数 奖励
            for (int j = before_day + 1; j <= 7; j++) {
                GiftPack giftPack = new GiftPack();
                giftPack.setKey(Constant.Gold + "");
                giftPack.setNum(login_gift_pack.getJSONObject(j + "").getIntValue(Constant.Gold + ""));
                giftPacks.add(giftPack);
            }


            //封装7天登录最终返回的数据
            LoginGiftPack loginGiftPack = new LoginGiftPack();
            loginGiftPack.setGiftPacks(giftPacks);
            loginGiftPack.setDays(login_days);
            loginGiftPack.setTodayIsGet(todayIsGet);
            return loginGiftPack;
        }
        return null;
    }


    /**
     * 离线收益
     *
     * @param userInfo 当前用户信息
     */
    public double getOffline(UsersEntity userInfo) {
        //用户离线时间（单位：s）
        int offlineTime = usersDao.getOfflineTime(userInfo.getId(), Constant.Gold_Sync_Time);

        //上限2小时
        if (offlineTime >= 7200) offlineTime = 7200;

        int offline_revenue = offlineTime * Constant.Offline_Gold;

        if (offline_revenue > 0) {
            userPackDao.updateNumByUserID(offline_revenue, userInfo.getId(), Constant.Gold);
            usersDao.updateOfflineCollectStatus(userInfo.getId());
        } else {
            offline_revenue = 0;
        }
        return offline_revenue;
    }

}
