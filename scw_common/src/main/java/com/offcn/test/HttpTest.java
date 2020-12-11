package com.offcn.test;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpTest {

    //通过代码的方式访问别人的系统
    public static void main(String[] args) throws IOException {

        //创建客户端对象
        HttpClient client = new DefaultHttpClient();

        //创建一个请求对象
        HttpGet httpGet = new HttpGet("https://www.baidu.com/");

        //发送请求，获得响应结果
        HttpResponse execute = client.execute(httpGet);

        //获取响应码
        int statusCode = execute.getStatusLine().getStatusCode();

        System.out.println("响应码为"+statusCode);

        String s = EntityUtils.toString(execute.getEntity());

        System.out.println(s);

    }

}
