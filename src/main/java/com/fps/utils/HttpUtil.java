package com.fps.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;


public class HttpUtil {

    Logger logger = LoggerFactory.getLogger(HttpUtil.class);



    public String post(String url, String params) throws IOException {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        try {
            HttpPost httppost = new HttpPost(url);
            httppost.addHeader("charset", "utf-8");
            httppost.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:42.0) Gecko/20100101 Firefox/42.0");

            byte[] bytes = params.getBytes(Charset.forName("utf-8"));
            ByteArrayEntity se = new ByteArrayEntity(bytes, ContentType.APPLICATION_JSON);

            httppost.setEntity(se);

            CloseableHttpResponse execute = closeableHttpClient.execute(httppost);
            HttpEntity httpEntity = execute.getEntity();
            String result = "";
            if (httpEntity != null) {
                result = EntityUtils.toString(httpEntity, "UTF-8").replace("\"{", "{").replace("}\"", "}").replaceAll("\\\\", "");
            }
            return result;
        } finally {
            closeableHttpClient.close();
        }
    }


    /**
     * get请求
     */
    public JSONObject get(String url) {

        DefaultHttpClient httpClient = new DefaultHttpClient();

        HttpGet httpGet = new HttpGet(url);
        JSONObject jsonObject = null;

        try {
            HttpResponse response = httpClient.execute(httpGet);

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity, "UTF-8");
                logger.info("get请求返回结果：{}", result);
                jsonObject = JSONObject.parseObject(result);
            }
        } catch (Exception e) {
            logger.error("出错信息：{}", e);
        }
        return jsonObject;
    }


    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String get(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("charset", "utf-8");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }


    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //1.获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            //2.中文有乱码的需要将PrintWriter改为如下
//            out=new OutputStreamWriter(conn.getOutputStream(),"UTF-8");
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }


    public static String httpsRequestToString(String path, String method, String body) {
        if (path == null || method == null) {
            return null;
        }
        String response = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        HttpsURLConnection conn = null;
        try {
            // 创建SSLConrext对象，并使用我们指定的信任管理器初始化
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            TrustManager[] tm = {new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

            }};
            sslContext.init(null, tm, new java.security.SecureRandom());

            // 从上面对象中得到SSLSocketFactory
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(path);
            conn = (HttpsURLConnection) url.openConnection();
            conn.setSSLSocketFactory(ssf);

            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);

            // 设置请求方式（get|post）
            conn.setRequestMethod(method);

            // 有数据提交时
            if (null != body) {
                OutputStream outputStream = conn.getOutputStream();
                outputStream.write(body.getBytes("UTF-8"));
                outputStream.close();
            }

            // 将返回的输入流转换成字符串
            inputStream = conn.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }

            response = buffer.toString();
        } catch (Exception e) {

        } finally {
            if (conn != null) {
                conn.disconnect();
            }
            try {
                bufferedReader.close();
                inputStreamReader.close();
                inputStream.close();
            } catch (IOException execption) {

            }
        }
        return response;
    }

}
