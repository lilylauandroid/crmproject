package com.fang.net;

import retrofit2.Call;

/**
 * 通用的api，需传入RequestEntity的引用
 * Created by User on 2019/1/15.
 */

public class BaseApi {

    public static NetClient netClient = NetClient.getInstance();

    public static BaseInterface baseInterface = netClient.create();

    public static Call<String> doGet(RequestEntity request){
        return baseInterface.executeGet(request.url,request.headerMap);
    }
    public static Call<String> doPost(RequestEntity request){
        return baseInterface.executePost(request.url,request.fieldMap,request.headerMap);
    }
}
