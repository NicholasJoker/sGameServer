package com.fps.controller;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.fps.entity.api.LocationAmapReq;
import com.fps.entity.api.LocationReq;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RestController
@RequestMapping("location")
class CrossOrigin {

    static final String key = "fljHqQy2nNvFle3ME7W3XP5xnljySedZ";

//    @RequestMapping
//    public Object getLocation(@RequestHeader(value = "openid") String openid,
//                          @RequestBody LocationReq req, HttpServletResponse resp){
////      String str= "http://api.map.baidu.com/reverse_geocoding/v3/?ak=您的ak&output=json&coordtype=wgs84ll&";
////      String uri="http://api.map.baidu.com/reverse_geocoding/v3/?ak="+key+"&callback=renderReverse&location=" + req.lat + "," + req.log + "&output=json&pois=0";
//        //百度的api
//        String uri="http://api.map.baidu.com/reverse_geocoding/v3/?ak="+key+"&output=json&coordtype=wgs84ll&location=" + req.lat + "," + req.log + "&output=json&pois=0";
//
//        String obj = HttpUtil.get(uri);
//        Json js = new Json(obj);
//        System.out.println("获取到的信息:"+obj);
//        return js;
//    }
    @RequestMapping
    public Object getLocation(@RequestHeader(value = "openid") String openid,
                                  @RequestBody LocationAmapReq req, HttpServletResponse resp){
        //高德的api
        //String uri="http://api.map.baidu.com/reverse_geocoding/v3/?ak="+key+"&output=json&coordtype=wgs84ll&location=" + req.lat + "," + req.log + "&output=json&pois=0";
        String uri= "https://restapi.amap.com/v3/geocode/regeo?output=xml&location="+ req.location+"&key="+req.key+"";
        System.out.println("");
        String obj = HttpUtil.get(uri);
//        Json js = new Json(obj);
        System.out.println("获取到的信息:"+obj);
        String [] str = obj.split("<city>");
        String city = "";
        if (str != null && str.length > 1) {
            str = str[1].split("</city>");
            if (str != null && str.length > 0) {
                city = str[0];
                Json js = new Json(city);
                System.out.println("str:"+ str[0]);
                return js;
            }
            else
            {
                System.out.println("城市地址返回null");
                return null;
            }
            // console.log("ipAddressCallback:",data)
        }
        else{
            System.out.println("城市地址返回null");
            return null;
        }

    }
//    public static void main(String[] args) {
//        float latitude=11;
//        float longitude=11;
//        String uri="http://api.map.baidu.com/reverse_geocoding/v3/?ak="+key+"&callback=renderReverse&location=" + latitude + "," + longitude + "&output=json&pois=0";
//        uri=new String(Base64.getEncoder().encode(uri.getBytes()));
//    }
}