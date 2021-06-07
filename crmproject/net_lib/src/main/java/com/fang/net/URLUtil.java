package com.fang.net;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.HttpUrl;

import static com.fang.net.NetClientConfig.MD5_CONSTANT;

/**
 * URL构建的工具类
 * Created by User on 2019/1/19.
 */

public class URLUtil {

    public static String creatBaseGetUrl(){
//        String SCHEME;
//        String HOST;
//        String HTTP_AGENT = NetClientConfig.HTTP_AGENT;
//        if (NetClientConfig.isCeshi){
//            SCHEME = NetClientConfig.HTTP_SCHEME;
//            HOST = NetClientConfig.HTTP_HOST_NEW;
//        }else {
//            SCHEME = NetClientConfig.HTTP_SCHEMES;
//            HOST = NetClientConfig.HTTPS_HOST_NEW;
//        }
//        HttpUrl.Builder builder = new HttpUrl.Builder();
//        builder.scheme(SCHEME).host(HOST).encodedPath(HTTP_AGENT);
//        return builder.build().toString();

//        Uri.Builder uriBuilder = new  Uri.Builder();
//        uriBuilder.scheme(SCHEME).encodedAuthority(HOST).appendEncodedPath(HTTP_AGENT);
//        return uriBuilder.build().toString();


        if (NetClientConfig.isCeshi){
            return NetClientConfig.URL_TEST;
        }else {
            return NetClientConfig.URL_FORMAL;
        }
    }

    /**
     * get请求url构建
     * @param parameters
     * @return
     */
    public static String creatGetUrl(Map<String,String> parameters){
//        String SCHEME;
//        String HOST;
//        String HTTP_AGENT = NetClientConfig.HTTP_AGENT;
        StringBuffer sb = null;
//        if (NetClientConfig.isCeshi){
//            SCHEME = NetClientConfig.HTTP_SCHEME;
//            HOST = NetClientConfig.HTTP_HOST_NEW;
//        }else {
//            SCHEME = NetClientConfig.HTTP_SCHEMES;
//            HOST = NetClientConfig.HTTPS_HOST_NEW;
//        }

//        HttpUrl.Builder builder = new HttpUrl.Builder();
//        builder.scheme(SCHEME).host(HOST).encodedPath(HTTP_AGENT);
//        Uri.Builder uriBuilder = new  Uri.Builder();
//        uriBuilder.scheme(SCHEME).encodedAuthority(HOST).appendEncodedPath(HTTP_AGENT);

        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            if (sb == null) {
                sb = new StringBuffer ();
            } else {
                sb.append("&");
            }

            try {
                sb.append(entry.getKey() + "=" + URLEncoder.encode(entry.getValue() != null ?  entry.getValue() : "","utf-8") );
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        String wirelesscode = getMD5Str(sb.toString() + MD5_CONSTANT);
        sb.append("&wirelesscode=" + wirelesscode);
        if (NetClientConfig.isCeshi){
            return NetClientConfig.URL_TEST + "?" + sb.toString();
        }else {
            return NetClientConfig.URL_FORMAL + "?" + sb.toString();
        }
    }

    /**
     * post请求url构建
     * @param parameters
     * @return
     */
    public static Map<String,String> creatPostUrl(Map<String,String> parameters){
//        String SCHEME;
//        String HOST;
//        String HTTP_AGENT = UtilsLog.HTTP_AGENT;
//        if (UtilsLog.isCeshi){
//            SCHEME = UtilsLog.HTTP_SCHEME;
//            HOST = UtilsLog.HTTP_HOST_NEW;
//        }else {
//            SCHEME = UtilsLog.HTTP_SCHEMES;
//            HOST = UtilsLog.HTTPS_HOST_NEW;
//        }
//        Uri.Builder uriBuilder = new  Uri.Builder();
//        uriBuilder.scheme(SCHEME).encodedAuthority(HOST).appendEncodedPath(HTTP_AGENT);
        Map<String,String> hhh = new LinkedHashMap<>(parameters);
//        for (Map.Entry<String, String> entry : hhh.entrySet()) {
//
////            uriBuilder.appendQueryParameter(entry.getKey(),entry.getValue());
//        }

        String wirelesscode = getMD5Str(hhh.get("messagename") + MD5_CONSTANT);
        hhh.put("wirelesscode",wirelesscode);
        return hhh;
    }


    /**
     *
     * @param str
     * @return
     */
    public static String getMD5Str(String str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] byteArray = messageDigest.digest();
        StringBuffer md5StrBuff = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
        }
        return md5StrBuff.toString();
    }
}
