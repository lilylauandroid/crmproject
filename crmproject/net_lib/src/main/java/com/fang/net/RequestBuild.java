package com.fang.net;

import java.util.Map;

/**
 * 请求实体类创建工具类
 * Created by User on 2019/2/25.
 */

public class RequestBuild {
    /**
     * get请求的实体类创建
     * @param queryMap  查询参数
     * @return 网络请求实体类
     */
    public static RequestEntity getQueryBuild(Map<String,String> queryMap){
        RequestEntity request = new RequestEntity();
        request.url = URLUtil.creatGetUrl(queryMap);
        request.headerMap = NetClientConfig.headerManager.getHerder();
        return request;
    }

    /**
     * post请求的实体类创建
     * @param fieldMap 表单参数
     * @return  网络请求实体类
     */
    public static RequestEntity postFieldBuild(Map<String,String> fieldMap){
        RequestEntity request = new RequestEntity();
        request.url = URLUtil.creatBaseGetUrl();
        request.fieldMap = URLUtil.creatPostUrl(fieldMap);
        request.headerMap = NetClientConfig.headerManager.getHerder();
        return request;
    }
}
