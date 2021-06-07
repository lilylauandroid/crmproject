package com.fang.net;

import java.util.Map;

/**
 * 网络请求实体类
 * Created by User on 2019/1/21.
 */

public class RequestEntity {

    public String requestType;//  get/post
    public String url;
    public Map<String,String> queryMap;
    public Map<String,String> headerMap;
    public Map<String,String> fieldMap;
}
