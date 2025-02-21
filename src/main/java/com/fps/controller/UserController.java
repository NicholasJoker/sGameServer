package com.fps.controller;

import com.alibaba.fastjson.JSONObject;
import com.fps.dao.*;
import com.fps.entity.LoginGiftRecordEntity;
import com.fps.entity.ProductsEntity;
import com.fps.entity.UserPackEntity;
import com.fps.entity.UsersEntity;
import com.fps.entity.api.*;
import com.fps.exceptions.HttpStatusException;
import com.fps.service.LoginService;
import com.fps.service.UserService;
import com.fps.utils.PublicUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
@RequestMapping("api/user")
@PropertySource(value = {"classpath:config.properties"})
@Api("用户相关接口")
public class UserController extends BaseController {

    @Autowired
    UsersDao usersDao;
    @Autowired
    UserPackDao userPackDao;
    @Autowired
    UserService userService;
    @Autowired
    LoginGiftRecordDao loginGiftRecordDao;
    @Autowired
    ProductsDao productsDao;
    @Autowired
    PurchaseRecordDao purchaseRecordDao;
    @Autowired
    LoginService loginService;
    @Autowired
    ShareStrategyDao shareStrategyDao;


    @PostMapping("/weChatAuthorize")
    @ApiOperation(value = "微信授权登录", notes = "微信授权登录", httpMethod = "POST",
            produces = MediaType.APPLICATION_JSON_VALUE, response = String.class)
    public JSONObject weChatAuthorize(@RequestBody WeChatAuthorize api, HttpServletResponse resp) throws Exception {

        String code = api.getCode();
        String openId = "";

        if (PublicUtil.isEmpty(code)) {
            HttpStatusException.httpStatusEx(410, "jsCode不能为空", resp);
            logger.warn("jsCode不能为空");
            return null;
        }

        JSONObject jsonObject = loginService.code2sessionKey(code, 1);
        if (PublicUtil.isNotEmpty(jsonObject.getInteger("errcode")) && jsonObject.getInteger("errcode") == 40029) {
            HttpStatusException.httpStatusEx(jsonObject.getInteger("errcode"), jsonObject.getString("errmsg"), resp);
            logger.warn(jsonObject.getString("errmsg"));
            return null;
        }

        openId = jsonObject.getString("openid");// 用户唯一标识

        JSONObject json = new JSONObject();
        json.put("openid", openId);

        logger.info("api/user/weChatAuthorize返回数据：openId：{}", json.toJSONString());
        return json;
    }
    @PostMapping("/qqAuthorize")
    @ApiOperation(value = "qq授权登录", notes = "qq授权登录", httpMethod = "POST",
            produces = MediaType.APPLICATION_JSON_VALUE, response = String.class)
    public JSONObject qqAuthorize(@RequestBody WeChatAuthorize api, HttpServletResponse resp) throws Exception {

        String code = api.getCode();
        String openId = "";

        if (PublicUtil.isEmpty(code)) {
            HttpStatusException.httpStatusEx(410, "jsCode不能为空", resp);
            logger.warn("jsCode不能为空");
            return null;
        }

        JSONObject jsonObject = loginService.code2sessionKey(code, 2);
        if (PublicUtil.isNotEmpty(jsonObject.getInteger("errcode")) && jsonObject.getInteger("errcode") == 40029) {
            HttpStatusException.httpStatusEx(jsonObject.getInteger("errcode"), jsonObject.getString("errmsg"), resp);
            logger.warn(jsonObject.getString("errmsg"));
            return null;
        }

         JSONObject json = new JSONObject();
        json.put("openid", openId);

        logger.info("api/user/qqAuthorize返回数据：openId：{}", json.toJSONString());
        return json;
    }

    @PostMapping("/headTAuthorize")
    @ApiOperation(value = "抖音头条授权登录", notes = "抖音头条授权登录", httpMethod = "POST",
            produces = MediaType.APPLICATION_JSON_VALUE, response = String.class)
    public JSONObject headTAuthorize(@RequestBody WeChatAuthorize api, HttpServletResponse resp) throws Exception {

        String code = api.getCode();
        String openId = "";

        if (PublicUtil.isEmpty(code)) {
            HttpStatusException.httpStatusEx(410, "jsCode不能为空", resp);
            logger.warn("jsCode不能为空");
            return null;
        }

        JSONObject jsonObject = loginService.code2sessionKey(code, 5);
        if (PublicUtil.isNotEmpty(jsonObject.getInteger("errcode")) && jsonObject.getInteger("errcode") == 40029) {
            HttpStatusException.httpStatusEx(jsonObject.getInteger("errcode"), jsonObject.getString("errmsg"), resp);
            logger.warn(jsonObject.getString("errmsg"));
            return null;
        }

        openId = jsonObject.getString("openid");// 用户唯一标识

        JSONObject json = new JSONObject();
        json.put("openid", openId);

        logger.info("api/user/headTAuthorize返回数据：openId：{}", json.toJSONString());
        return json;
    }
    @PostMapping("/OPPOAuthorize")
    @ApiOperation(value = "OPPO授权登录", notes = "OPPO授权登录", httpMethod = "POST",
            produces = MediaType.APPLICATION_JSON_VALUE, response = String.class)
    public JSONObject OPPOAuthorize(@RequestBody WeChatAuthorize api, HttpServletResponse resp) throws Exception {

        String code = api.getCode();
        String openId = "";

        if (PublicUtil.isEmpty(code)) {
            HttpStatusException.httpStatusEx(410, "jsCode不能为空", resp);
            logger.warn("jsCode不能为空");
            return null;
        }

        JSONObject jsonObject = loginService.code2sessionKey(code, 3);
        if (PublicUtil.isNotEmpty(jsonObject.getInteger("errcode")) && jsonObject.getInteger("errcode") == 40029) {
            HttpStatusException.httpStatusEx(jsonObject.getInteger("errcode"), jsonObject.getString("errmsg"), resp);
            logger.warn(jsonObject.getString("errmsg"));
            return null;
        }

        openId = jsonObject.getString("openid");// 用户唯一标识

        JSONObject json = new JSONObject();
        json.put("openid", openId);

        logger.info("api/user/OPPOAuthorize返回数据：openId：{}", json.toJSONString());
        return json;
    }

    @PostMapping("/VIVOAuthorize")
    @ApiOperation(value = "VIVO授权登录", notes = "VIVO授权登录", httpMethod = "POST",
            produces = MediaType.APPLICATION_JSON_VALUE, response = String.class)
    public JSONObject VIVOAuthorize(@RequestBody WeChatAuthorize api, HttpServletResponse resp) throws Exception {

        String code = api.getCode();
        String openId = "";

        if (PublicUtil.isEmpty(code)) {
            HttpStatusException.httpStatusEx(410, "jsCode不能为空", resp);
            logger.warn("jsCode不能为空");
            return null;
        }

        JSONObject jsonObject = loginService.code2sessionKey(code, 4);
        if (PublicUtil.isNotEmpty(jsonObject.getInteger("errcode")) && jsonObject.getInteger("errcode") == 40029) {
            HttpStatusException.httpStatusEx(jsonObject.getInteger("errcode"), jsonObject.getString("errmsg"), resp);
            logger.warn(jsonObject.getString("errmsg"));
            return null;
        }

        openId = jsonObject.getString("openid");// 用户唯一标识

        JSONObject json = new JSONObject();
        json.put("openid", openId);

        logger.info("api/user/VIVOAuthorize返回数据：openId：{}", json.toJSONString());
        return json;
    }

    @PostMapping("/login")
    @ApiOperation(value = "游客登录", notes = "游客登录", httpMethod = "POST",
            produces = MediaType.APPLICATION_JSON_VALUE, response = LoginResp.class)
    public LoginResp login(@RequestHeader(value = "openid") String openid,
                           @RequestBody LoginReq api, HttpServletResponse resp) throws Exception {

        Integer platformType = api.getPlatformType();
        if (PublicUtil.isEmpty(platformType)) platformType = 1;

        //logger.debug("游客登录输出openid：",openid);
        if (PublicUtil.isEmpty(openid)) {
            HttpStatusException.httpStatusEx(403, "无效openid", resp);
            logger.warn("用户openid不能为空");
            return null;
        }

        //根据openId 查询用户信息
        UsersEntity userInfo = usersDao.queryByOpenid(openid);
        if (PublicUtil.isEmpty(userInfo)) {
            userInfo = new UsersEntity();

            String head_img = "https://wx.qlogo.cn/mmopen/vi_32/PiajxSqBRaEJGqOCicsDkjvC2jBBhicfFGYX8UMiaMGsg0jJTbPgwOHCOfBmPegM66rwNmlFTvk50wQgGlttU2LZCg/132";
            String username = "用户_" + (int) (Math.random() * 90000000 + 10000000);

            usersDao.insert(username, head_img, "", openid, Constant.Init_Gun, platformType, userInfo);
            int user_id = userInfo.getId();

            userPackDao.insert(user_id, Constant.Init_Gun, 1);
            userPackDao.insert(user_id, Constant.Gold, 1000);
            userPackDao.insert(user_id, Constant.Diamond, 0);
            userInfo = usersDao.getById(userInfo.getId());
        }

        User user = new User(userInfo.getId(), userInfo.getUsername(), userInfo.getHead_img(), openid,
                userInfo.getYouke_openid(), userInfo.getPlatform_type());

        //封装数据
        LoginResp loginResp = new LoginResp();
        loginResp.setUser(user);
        loginResp.setUserPack(userService.getUserPack(userInfo.getId()));
        loginResp.setShareVideoCfg(shareStrategyDao.getShareVideoCfgByType(platformType));
        loginResp.setOfflineRevenue(userService.getOffline(userInfo));
        String[] guide = new String[]{};
        String guideStr = userInfo.getGuide();
        if (guideStr != null && !"".equals(guideStr)) {
            guide = guideStr.split(",");
        }
        loginResp.setGuide(guide);
        LoginGiftPack sevenDayGiftPack = userService.getSevenDayGiftPack(userInfo);
        if (PublicUtil.isNotEmpty(sevenDayGiftPack)) {
            loginResp.setLoginGiftPack(sevenDayGiftPack);
        }
        else{

        }
        userPackDao.updateGoldUpdatedTime(userInfo.getId());
        return loginResp;
    }


    @PostMapping("/channelLogin")
    @ApiOperation(value = "第三方渠道登录", notes = "第三方渠道登录", httpMethod = "POST",
            produces = MediaType.APPLICATION_JSON_VALUE, response = LoginResp.class)
    public LoginResp channelLogin(@RequestHeader(value = "openid") String openid,
                                  @RequestHeader(value = "youkeOpenid", required = false) String youkeOpenid,
                                  @RequestBody LoginReq api, HttpServletResponse resp) throws Exception {

        if (PublicUtil.isEmpty(openid) && PublicUtil.isEmpty(youkeOpenid)) {
            HttpStatusException.httpStatusEx(403, "无效openid", resp);
            logger.warn("用户openid不能为空");
            return null;
        }

        UsersEntity userInfo = new UsersEntity();

        logger.info("打印输出userInfo:",userInfo);
        //为空时新增用户
        if (PublicUtil.isEmpty(youkeOpenid)) {

            //查询当前openid是否入库
            userInfo = usersDao.getByOpenid(openid);

            if (PublicUtil.isEmpty(userInfo)) {
                userInfo = new UsersEntity();
                usersDao.insert(api.getUsername(), api.getHeadImg(), openid, "", Constant.Init_Gun, api.getPlatformType(), userInfo);
                int user_id = userInfo.getId();
                userPackDao.insert(user_id, Constant.Init_Gun, 1);
                userPackDao.insert(user_id, Constant.Gold, 1000);
                userPackDao.insert(user_id, Constant.Diamond, 0);
                userInfo = usersDao.getById(userInfo.getId());
            }
        } else {
            //根据openId 查询用户信息
            userInfo = usersDao.queryByYoukeOpenid(youkeOpenid);
            if (PublicUtil.isEmpty(userInfo)) {
                HttpStatusException.httpStatusEx(514, "无此游客", resp);
                return null;
            }
            usersDao.relationOpenid(openid, api.getPlatformType(), api.getUsername(), api.getHeadImg(), userInfo.getId());
        }


        //重新查询数据
        userInfo = usersDao.getById(userInfo.getId());

        User user = new User(userInfo.getId(), userInfo.getUsername(), userInfo.getHead_img(), userInfo.getOpenid(),
                userInfo.getYouke_openid(), userInfo.getPlatform_type());


        //封装数据
        LoginResp loginResp = new LoginResp();
        loginResp.setUser(user);
        loginResp.setUserPack(userService.getUserPack(userInfo.getId()));
        loginResp.setShareVideoCfg(shareStrategyDao.getShareVideoCfgByType(api.getPlatformType()));
        loginResp.setOfflineRevenue(userService.getOffline(userInfo));

        String[] guide = new String[]{};
        String guideStr = userInfo.getGuide();
        if (guideStr != null && !"".equals(guideStr)) {
            guide = guideStr.split(",");
        }
        loginResp.setGuide(guide);

        LoginGiftPack sevenDayGiftPack = userService.getSevenDayGiftPack(userInfo);
        if (PublicUtil.isNotEmpty(sevenDayGiftPack)) {
            loginResp.setLoginGiftPack(sevenDayGiftPack);
        }

        userPackDao.updateGoldUpdatedTime(userInfo.getId());

        logger.info("api/user/login返回数据：{}", loginResp.toString());
        return loginResp;
    }


    @PostMapping("/changeGun")
    @ApiOperation(value = "改变选择的枪", notes = "改变选择的枪", httpMethod = "POST",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONObject changeGun(@RequestHeader(value = "openid", required = false) String openid,
                                @RequestHeader(value = "gold", required = false) double gold,
                                @RequestBody ChangeGunReq api, HttpServletResponse resp) throws Exception {
        logger.info("api/user/changeGun请求数据：{}，openid：{}", api.toString(), openid);

        UsersEntity userInfo = getUserInfo(openid, resp);

        usersDao.syncGoldNum(gold, userInfo.getId(), Constant.Gold);


        int isHaveGoods = userPackDao.checkIsHaveGoods(userInfo.getId(), api.getNewGunId());
        if (isHaveGoods <= 0) {
            logger.warn("用户:{} 没有此物品: {}", userInfo.getId(), api.getNewGunId());
            HttpStatusException.httpStatusEx(517, "用户没有此物品", resp);
            return null;
        }
        usersDao.changeDefaultGun(api.getNewGunId(), userInfo.getId());


        JSONObject json = new JSONObject();
        json.put("chgGoods", userService.getChangeGoodsInfo(userInfo.getId(), Constant.Select_Gun_Id));
        return json;
    }


    @PostMapping("/delGun")
    @ApiOperation(value = "删除背包中的枪", notes = "删除背包中的枪", httpMethod = "POST",
            produces = MediaType.APPLICATION_JSON_VALUE, response = Ajax.class)
    public JSONObject delGun(@RequestHeader(value = "openid", required = false) String openid,
                             @RequestHeader(value = "gold", required = false) double gold,
                             @RequestBody DelGunReq api, HttpServletResponse resp) throws Exception {

        //获取用户信息
        UsersEntity userInfo = getUserInfo(openid, resp);

        //同步客户端金币
        usersDao.syncGoldNum(gold, userInfo.getId(), Constant.Gold);


        //删除用户背包中的枪
        int i = userPackDao.delGoodsByUserId(userInfo.getId(), api.getRemoveGunId());
        if (i > 0) {
            //查询该枪的购买金币是多少
            ProductsEntity productsEntity = productsDao.queryByGoodsId(api.getRemoveGunId());
            double num = productsEntity.getPrice() / 2;
            userPackDao.updateNumByUserID(num, userInfo.getId(), Constant.Gold);
        }

        JSONObject js = new JSONObject();
        js.put("gunPackage", userService.getChangeGoodsInfo(userInfo.getId(), Constant.Gun_Prefix));
        js.putAll(userService.getChangeGoodsInfo(userInfo.getId(), Constant.Gold));
        JSONObject json = new JSONObject();
        json.put("chgGoods", js);
        return json;
    }


    @PostMapping("/unlockChapterLevel")
    @ApiOperation(value = "存储当前过关关卡数", notes = "存储当前过关关卡数", httpMethod = "POST",
            produces = MediaType.APPLICATION_JSON_VALUE, response = Ajax.class)
    public JSONObject saveCurLevel(@RequestHeader(value = "openid", required = false) String openid,
                                   @RequestHeader(value = "gold", required = false) double gold,
                                   @RequestBody SaveCurLevelReq api, HttpServletResponse resp) throws Exception {
        logger.info("api/user/unlockChapterLevel请求数据：{}，openid：{}", api.toString(), openid);

        //获取用户信息
        UsersEntity userInfo = getUserInfo(openid, resp);

        //同步客户端金币
        usersDao.syncGoldNum(gold, userInfo.getId(), Constant.Gold);

        usersDao.updateLevelChapter(api.getLevel(), api.getChapter(), userInfo.getId());


        JSONObject json = new JSONObject();
        json.put("chgGoods", userService.getChangeGoodsInfo(userInfo.getId(), Constant.Unlock_Chapter_Level));
        return json;
    }


    @PostMapping("/changeGoodsNum")
    @ApiOperation(value = "背包通用物品数量增/减", notes = "背包通用物品数量增/减（增：正数；减：负数）", httpMethod = "POST",
            produces = MediaType.APPLICATION_JSON_VALUE, response = String.class)
    public JSONObject changeGoodsNum(@RequestHeader(value = "openid", required = false) String openid,
                                     @RequestHeader(value = "gold", required = false) double gold,
                                     @RequestBody ChangeGoodsNumReq api, HttpServletResponse resp) throws Exception {

        //获取用户信息
        UsersEntity userInfo = getUserInfo(openid, resp);

        //同步客户端金币
        usersDao.syncGoldNum(gold, userInfo.getId(), Constant.Gold);

        UserPackEntity userPackEntity = userPackDao.getUserPackGoods(userInfo.getId(), api.getGoodsId());
        if (PublicUtil.isEmpty(userPackEntity)) {
            userPackDao.insert(userInfo.getId(), api.getGoodsId(), 0);
        }

        int i = userPackDao.updateNumByUserID(api.getNum(), userInfo.getId(), api.getGoodsId());
        if (i <= 0) {
            HttpStatusException.httpStatusEx(512, "当前数量不足", resp);
            return null;
        }


        JSONObject json = new JSONObject();
        switch (api.getGoodsId() / 10000) {
            case Constant.Currency_Prefix:
                json.put("chgGoods", userService.getChangeGoodsInfo(userInfo.getId(), Constant.Gold));
                break;
            case Constant.Gun_Prefix:
                JSONObject js = new JSONObject();
                js.put("gunPackage", userService.getChangeGoodsInfo(userInfo.getId(), Constant.Gun_Prefix));
                json.put("chgGoods", js);
                break;
        }
        return json;
    }


    @PostMapping("/getGoldNum")
    @ApiOperation(value = "获取用户当前的金币数量", notes = "获取用户当前的金币数量", httpMethod = "POST",
            produces = MediaType.APPLICATION_JSON_VALUE, response = Double.class)
    public Double getGoldNum(@RequestHeader(value = "openid", required = false) String openid,
                             @RequestHeader(value = "gold", required = false) double gold,
                             @RequestBody RequestApi api, HttpServletResponse resp) throws Exception {
        UsersEntity userInfo = getUserInfo(openid, resp);
        //同步客户端金币
        usersDao.syncGoldNum(gold, userInfo.getId(), Constant.Gold);
        return userService.getGoldNum(userInfo.getId());
    }


    @PostMapping("/getSignInReward")
    @ApiOperation(value = "领取签到奖励", notes = "领取签到奖励", httpMethod = "POST",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONObject getSignInReward(@RequestHeader(value = "openid", required = false) String openid,
                                      @RequestHeader(value = "gold", required = false) double gold,
                                      @RequestBody SignInRewardReq api, HttpServletResponse resp) throws Exception {

        UsersEntity userInfo = getUserInfo(openid, resp);
        //同步客户端金币
        usersDao.syncGoldNum(gold, userInfo.getId(), Constant.Gold);

        //是否翻倍领取；true：是
        boolean doubleGet = api.isDoubleGet();

        int login_days = userInfo.getLogin_days();

        LoginGiftRecordEntity loginGiftRecordEntity = loginGiftRecordDao.queryTodayReward(userInfo.getId(), login_days);
        if (PublicUtil.isEmpty(loginGiftRecordEntity)) {
            HttpStatusException.httpStatusEx(513, "你已领取奖励", resp);
            return null;
        }

        //奖励领取
        int receive_num = loginGiftRecordEntity.getNum();
        if (doubleGet) receive_num = receive_num * 2;

        userPackDao.updateNumByUserID(receive_num, userInfo.getId(), Constant.Gold);
        loginGiftRecordDao.updateReceiveStatus(loginGiftRecordEntity.getId(), receive_num);

        JSONObject json = new JSONObject();
        json.put("chgGoods", userService.getChangeGoodsInfo(userInfo.getId(), Constant.Gold));
        return json;
    }


    @PostMapping("/purchaseGun")
    @ApiOperation(value = "购买枪支", notes = "购买枪支", httpMethod = "POST",
            produces = MediaType.APPLICATION_JSON_VALUE, response = Ajax.class)
    public JSONObject purchaseGun(@RequestHeader(value = "openid", required = false) String openid,
                                  @RequestHeader(value = "gold", required = false) double gold,
                                  @RequestBody PurchaseGunReq api, HttpServletResponse resp) throws Exception {

        UsersEntity userInfo = getUserInfo(openid, resp);
        int user_id = userInfo.getId();

        //同步客户端金币
        usersDao.syncGoldNum(gold, userInfo.getId(), Constant.Gold);

        //购买的商品ID
        int goods_id = api.getBuyGunId();


        int buy_times = purchaseRecordDao.countBuyTimesGoodsId(user_id, goods_id);


        //查询当前购买的商品信息
        ProductsEntity productsEntity = productsDao.queryByGoodsId(api.getBuyGunId());
        if (PublicUtil.isEmpty(productsEntity)) {
            HttpStatusException.httpStatusEx(516, "购买的商品不存在", resp);
            return null;
        }

        double rate = Math.pow(Constant.Purchase_Accumulation_Rate, buy_times);
        rate = rate == 0 ? 1 : rate;
        double price = productsEntity.getPrice() * rate;
        double actual_price = price * -1;
        int i = userPackDao.updateNumByUserID(actual_price, user_id, Constant.Gold);
        if (i <= 0) {
            HttpStatusException.httpStatusEx(520, "用户金币数量不足", resp);
            return null;
        }

        int add_status = userPackDao.updateNumByUserID(1, user_id, goods_id);
        if (add_status <= 0) {
            userPackDao.insert(user_id, goods_id, 1);
        }
        purchaseRecordDao.save(user_id, productsEntity.getId(), goods_id, price);

        //查询修改后的背包数据
        JSONObject js = new JSONObject();
        js.put("gunPackage", userService.getChangeGoodsInfo(userInfo.getId(), Constant.Gun_Prefix));
        js.putAll(userService.getChangeGoodsInfo(userInfo.getId(), Constant.Gold));
        js.put("buyedGunInfo", userService.getPurchaseRecord(userInfo.getId()));

        JSONObject json = new JSONObject();
        json.put("chgGoods", js);
        return json;
    }


    @PostMapping("/syncGold")
    @ApiOperation(value = "同步用户金币数量", notes = "同步用户金币数量", httpMethod = "POST",
            produces = MediaType.APPLICATION_JSON_VALUE, response = JSONObject.class)
    public JSONObject syncGold(@RequestHeader(value = "openid", required = false) String openid,
                               @RequestHeader(value = "gold", required = false) double gold,
                               @RequestBody SyncGoldReq api, HttpServletResponse resp) throws Exception {

        //获取用户信息
        UsersEntity userInfo = getUserInfo(openid, resp);

        //同步客户端用户金币数
        usersDao.syncGoldNum(api.getNum(), userInfo.getId(), Constant.Gold);

        JSONObject json = new JSONObject();
        json.put("chgGoods", userService.getChangeGoodsInfo(userInfo.getId(), Constant.Currency_Prefix));
        return json;
    }


    @PostMapping("/syntheticGun")
    @ApiOperation(value = "合成枪", notes = "合成枪", httpMethod = "POST",
            produces = MediaType.APPLICATION_JSON_VALUE, response = JSONObject.class)
    public JSONObject syntheticGun(@RequestHeader(value = "openid", required = false) String openid,
                                   @RequestHeader(value = "gold", required = false) double gold,
                                   @RequestBody SyntheticGunReq api, HttpServletResponse resp) throws Exception {

        //获取用户信息
        UsersEntity userInfo = getUserInfo(openid, resp);

        //同步客户端金币
        usersDao.syncGoldNum(gold, userInfo.getId(), Constant.Gold);

        int i = userPackDao.updateNumByUserID(-2, userInfo.getId(), api.getGunId());
        if (i <= 0) {
            HttpStatusException.httpStatusEx(515, "数量不足", resp);
            return null;
        }


        //合成的枪ID
        int goods_id = api.getGunId() + 1;


        UserPackEntity userPackEntity = userPackDao.getUserPackGoods(userInfo.getId(), goods_id);
        if (PublicUtil.isEmpty(userPackEntity)) {
            userPackDao.insert(userInfo.getId(), goods_id, 0);

            usersDao.changeDefaultGun(goods_id, userInfo.getId());
        }
        userPackDao.updateNumByUserID(1, userInfo.getId(), goods_id);


        JSONObject js = new JSONObject();
        js.put("gunPackage", userService.getChangeGoodsInfo(userInfo.getId(), Constant.Gun_Prefix));
        js.putAll(userService.getChangeGoodsInfo(userInfo.getId(), Constant.Select_Gun_Id));

        JSONObject json = new JSONObject();
        json.put("chgGoods", js);
        return json;
    }


    @PostMapping("/supplyBox")
    @ApiOperation(value = "补满空投箱", notes = "补满空投箱", httpMethod = "POST",
            produces = MediaType.APPLICATION_JSON_VALUE, response = JSONObject.class)
    public JSONObject supplyBox(@RequestHeader(value = "openid", required = false) String openid,
                                @RequestHeader(value = "gold", required = false) double gold,
                                HttpServletResponse resp) throws Exception {
        logger.info("api/user/supplyBox请求openid：{}", openid);

        //获取用户信息
        UsersEntity userInfo = getUserInfo(openid, resp);

        usersDao.syncGoldNum(gold, userInfo.getId(), Constant.Gold);


        usersDao.updateKongtouGunNum(8, userInfo.getId());


        UsersEntity entity = usersDao.getById(userInfo.getId());

        JSONObject js = new JSONObject();
        js.put("kongtouGunNum", entity.getKongtou_gun_num());

        JSONObject json = new JSONObject();
        json.put("chgGoods", js);
        return json;
    }


    @PostMapping("/receiveAirdrop")
    @ApiOperation(value = "领取空投（点击空投按钮）", notes = "领取空投（点击空投按钮）", httpMethod = "POST",
            produces = MediaType.APPLICATION_JSON_VALUE, response = JSONObject.class)
    public JSONObject receiveAirdrop(@RequestHeader(value = "openid", required = false) String openid,
                                     @RequestHeader(value = "gold", required = false) double gold,
                                     HttpServletResponse resp) throws Exception {

        //获取用户信息
        UsersEntity userInfo = getUserInfo(openid, resp);

        //同步客户端金币
        usersDao.syncGoldNum(gold, userInfo.getId(), Constant.Gold);


        //查询当前用户背包枪的数量
        int total_gun = userPackDao.countGunTotal(userInfo.getId());
        if (total_gun >= Constant.Max_Gun_Pack) {
            HttpStatusException.httpStatusEx(519, "当前合成格子已满，无法获得空投箱中的武器！", resp);
            return null;
        }

        int i = usersDao.reduceKongtouGunNum(userInfo.getId());
        if (i <= 0) {
            logger.warn("空投箱数量不足");
            HttpStatusException.httpStatusEx(518, "空投箱数量不足", resp);
            return null;
        }


        int gain_gun_id = Constant.Init_Gun;

        double current_gold = userPackDao.getGoodsNum(userInfo.getId(), Constant.Gold);
        ProductsEntity maxGun = productsDao.getMaxGun(current_gold);
        if (PublicUtil.isNotEmpty(maxGun)) {
            gain_gun_id = maxGun.getGoods_id();
        }

        UserPackEntity userPackEntity = userPackDao.getUserPackGoods(userInfo.getId(), gain_gun_id);
        if (PublicUtil.isEmpty(userPackEntity)) {
            userPackDao.insert(userInfo.getId(), gain_gun_id, 0);
        }
        userPackDao.updateNumByUserID(1, userInfo.getId(), gain_gun_id);


        //返回数据
        UsersEntity entity = usersDao.getById(userInfo.getId());

        JSONObject js = new JSONObject();
        js.put("kongtouGunNum", entity.getKongtou_gun_num());
        js.put("gunPackage", userService.getChangeGoodsInfo(userInfo.getId(), Constant.Gun_Prefix));

        JSONObject json = new JSONObject();
        json.put("chgGoods", js);
        return json;
    }


    @PostMapping("/getRankingList")
    @ApiOperation(value = "世界排行榜", notes = "世界排行榜", httpMethod = "POST",
            produces = MediaType.APPLICATION_JSON_VALUE, response = JSONObject.class)
    public List<UsersEntity> getRankingList(@RequestHeader(value = "openid", required = false) String openid,
                                            @RequestHeader(value = "gold", required = false) double gold,
                                            @RequestBody RankingListReq api, HttpServletResponse resp) throws Exception {
        logger.info("api/user/getRankingList请求参数：{}，openid：{}", api.toString(), openid);
        //获取用户信息
        UsersEntity userInfo = getUserInfo(openid, resp);

        //同步客户端金币
        usersDao.syncGoldNum(gold, userInfo.getId(), Constant.Gold);

        List<UsersEntity> rankingList = usersDao.getRankingList(api.getPlatformType());

        return rankingList;
    }


    @PostMapping("/completeGuide")
    @ApiOperation(value = "完成教程步骤", notes = "完成教程步骤", httpMethod = "POST",
            produces = MediaType.APPLICATION_JSON_VALUE, response = JSONObject.class)
    public JSONObject completeGuide(@RequestHeader(value = "openid", required = false) String openid,
                                    @RequestHeader(value = "gold", required = false) double gold,
                                    @RequestBody CompleteGuideReq api, HttpServletResponse resp) throws Exception {
        UsersEntity userInfo = getUserInfo(openid, resp);
        usersDao.syncGoldNum(gold, userInfo.getId(), Constant.Gold);
        String step = api.getStep();
        if (step != null && !"".equals(step)) {
            String guide = userInfo.getGuide();
            if (guide == null || "".equals(guide)) {
                usersDao.updateGuide(step, userInfo.getId());
            } else {
                String[] split = guide.split(",");
                int i = 0;
                for (; i < split.length; i++) {
                    if (split[i].equals(step)) {
                        break;
                    }
                }
                if (i >= split.length)
                    usersDao.updateGuide(guide + "," + step, userInfo.getId());
            }
        }

        UsersEntity entity = usersDao.getById(userInfo.getId());

        String[] guide = new String[]{};
        String guideStr = entity.getGuide();
        if (guideStr != null && !"".equals(guideStr)) {
            guide = guideStr.split(",");
        }
        JSONObject js = new JSONObject();
        js.put("guide", guide);
        JSONObject json = new JSONObject();
        json.put("chgGoods", js);
        return json;
    }


}
