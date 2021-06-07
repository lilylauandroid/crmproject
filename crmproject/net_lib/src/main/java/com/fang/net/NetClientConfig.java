package com.fang.net;

import java.util.concurrent.TimeUnit;

/**
 * 网络的基本配置类
 * Created by User on 2019/1/14.
 */

public class NetClientConfig {

//    public static  String HTTP_SCHEME = "http";
//    public static  String HTTP_SCHEMES = "https";
//
//    public static  String HTTPS_HOST_NEW ="jjy.3g.fang.com/httpclient";
//
//
//    //测试地址
//    public static  String HTTP_HOST_NEW ="124.251.47.220:9000/httpclient";
//
//    public static  String HTTP_AGENT = "/agentservice.jsp";

    public static  String URL_TEST;
    public static  String URL_FORMAL;
    /**
     * true 测试站  false  正式站
     */
    public static boolean isCeshi;
    /**
     * BASE_URL
     */
    public static String BASE_URL;
    /**
     * 超时时间
     */
    public static long TIME_OUT;
    /**
     * 超时时间单位
     */
    public static TimeUnit TIME_UNIT;

    public static String MD5_CONSTANT;
    public static HeaderManager headerManager;
//    public static void setHeaderManager(HeaderManager headerManager){
//        headerManager = headerManager;
//    }
//    public static class Builder{
//    public Builder() {
//
//    }
//}
}
