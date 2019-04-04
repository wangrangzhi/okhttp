package redisandkfka.demo.controller;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.io.FileUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redisandkfka.demo.util.okhttp.LoggingInterceptor;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController

@RequestMapping("/sparkWebsite/api/v1/allkindsofhttp")
public class allkindsofhttp {


    //grpc
    //

    //jetty
    //netty
    //
    //rxjava
    //thrift
    //vertx

    //redis-redisson

    //dbcp2
    //mybatis
    //kafka


    @PostMapping("/jdkhttp")
    public String trafficTotalrefashEverySenondsRedisRewrite(@RequestParam(value = "serveNameList") String url)
            throws ParseException, InvocationTargetException, IllegalAccessException {

        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                //    System.out.println(key + "--->" + map.get(key));
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


    @PostMapping("/google-httpclient")
    public String googlehttpclient(@RequestParam(value = "serveNameList") String url) throws IOException {
        HttpRequestFactory requestFactory
                = new NetHttpTransport().createRequestFactory();
        HttpRequest request = requestFactory.buildGetRequest(
                new GenericUrl("https://github.com"));
        String rawResponse = request.execute().parseAsString();

        return rawResponse;
    }


    @PostMapping("/httpclient")
    public String httpclient(@RequestParam(value = "serveNameList") String url) throws IOException {
        String content = new String();
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建http GET请求
        HttpGet httpGet = new HttpGet("http://www.baidu.com");
        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                //请求体内容
                content = EntityUtils.toString(response.getEntity(), "UTF-8");
                //内容写入文件
                FileUtils.writeStringToFile(new File("E:\\devtest\\baidu.html"), content, "UTF-8");
                System.out.println("内容长度：" + content.length());
            }
        } finally {
            if (response != null) {
                response.close();
            }
            //相当于关闭浏览器
            httpclient.close();

        }
        return content;
    }

    OkHttpClient client = new OkHttpClient.Builder()
//            .eventListener(new PrintingEventListener())
            .addInterceptor(new LoggingInterceptor())
            .build();

    @PostMapping("/okhttp")
    public String okhttp(@RequestParam(value = "serveNameList") String url) throws IOException {


        Request request = new Request.Builder()
                .url("http://www.baidu.com")
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

}



