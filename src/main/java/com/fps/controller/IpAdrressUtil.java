package com.fps.controller;

import cn.hutool.http.HttpUtil;
import io.swagger.annotations.Api;
import org.lionsoul.ip2region.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.lang.reflect.Method;
import java.net.InetAddress;

@RestController
@RequestMapping
@Api("IP定位的相关接口")
public class IpAdrressUtil {
    /**
     * 获取Ip地址
     * @param request
     * @return
     */
    //static final String key = "fljHqQy2nNvFle3ME7W3XP5xnljySedZ";
    //static final String amap_key = "28bd595aff25143ed01d421103b73a57";
    @PostMapping("/getIpAdrress")
    private static Object getIpAdrress(HttpServletRequest request,@RequestBody String key) {

//        String last = key.substring(key.length()-2,key.length()-1);
//        System.out.println("last:"+last);
        String ampkey = key;
        if(key.endsWith("="))
        {
            ampkey = key.substring(0,key.length()-1);
            System.out.println(ampkey);
        }
        System.out.println("AK:"+key);
        System.out.println("AK:"+ampkey);
        try {
            String ip = request.getHeader("X-Forwarded-For");
            if (ip == null || ip.length() == 0
                    || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0
                    || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0
                    || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
                if (ip.equals("127.0.0.1")||ip.equals("0:0:0:0:0:0:0:1")) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = InetAddress.getLocalHost();
                    ip = inet.getHostAddress();

                    // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
                    if (ip != null && ip.length() > 15) { // "***.***.***.***".length()
                        // = 15
                        if (ip.indexOf(",") > 0) {
                            ip = ip.substring(0, ip.indexOf(","));
                        }
                    }
                }
            }
            if (ip != null && ip.length() > 15) { // "***.***.***.***".length()
                // = 15
                if (ip.indexOf(",") > 0) {
                    ip = ip.substring(0, ip.indexOf(","));
                }
            }
//            //得到ip 然后获取地址
//            ip = "114.247.50.2";
            System.out.println("获取到的ip信息:"+ip);
            //String obj = getCityInfo(ip);
//            //百度地图(IP定位目前有问题)
//            String url = "http://api.map.baidu.com/location/ip?ak="+key+"&ip="+ip+"&coor=gcj02";
            //System.out.println(url);
            //amap 高德地图
            String url = "https://restapi.amap.com/v3/ip?ip="+ip+"&output=xml&key="+ampkey;
            System.out.println(url);
            String obj = HttpUtil.get(url);
//          obj =new String(obj.getBytes(),"UTF-8");
            Json js = new Json(obj);
            System.out.println("获取到的位置信息:"+obj);
            return js;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static String getCityInfo(String ip){

        //db
        String dbPath = IPUtil.class.getResource("/ip2region.db").getPath();

        File file = new File(dbPath);
        if ( file.exists() == false ) {
            System.out.println("Error: Invalid ip2region.db file");
        }

        //查询算法
        int algorithm = DbSearcher.BTREE_ALGORITHM; //B-tree
        //DbSearcher.BINARY_ALGORITHM //Binary
        //DbSearcher.MEMORY_ALGORITYM //Memory
        try {
            DbConfig config = new DbConfig();
            DbSearcher searcher = new DbSearcher(config, dbPath);

            //define the method
            Method method = null;
            switch ( algorithm )
            {
                case DbSearcher.BTREE_ALGORITHM:
                    method = searcher.getClass().getMethod("btreeSearch", String.class);
                    break;
                case DbSearcher.BINARY_ALGORITHM:
                    method = searcher.getClass().getMethod("binarySearch", String.class);
                    break;
                case DbSearcher.MEMORY_ALGORITYM:
                    method = searcher.getClass().getMethod("memorySearch", String.class);
                    break;
            }

            DataBlock dataBlock = null;
            if ( Util.isIpAddress(ip) == false ) {
                System.out.println("Error: Invalid ip address");
            }

            dataBlock  = (DataBlock) method.invoke(searcher, ip);

            return dataBlock.getRegion();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}