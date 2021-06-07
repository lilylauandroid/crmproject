package com.fang.net;

import java.util.Map;

import retrofit2.Call;

/**
 * 便于各云使用的api，直接传入参数的map
 * Created by User on 2019/1/19.
 */

public class CloudBaseApi {

    public static Call<String> doGet(Map<String,String> queryMap){

        return BaseApi.doGet(RequestBuild.getQueryBuild(queryMap));
    }

    public static Call<String> doPost(Map<String,String> fieldMap){
        return BaseApi.doPost(RequestBuild.postFieldBuild(fieldMap));
    }

}
