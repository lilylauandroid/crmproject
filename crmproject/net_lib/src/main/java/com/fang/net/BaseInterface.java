package com.fang.net;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface BaseInterface {
    @GET()
    Call<String> executeGet(@Url String url, @QueryMap Map<String, String> maps, @HeaderMap Map<String,String> headers);

    @GET()
    Call<String> executeGet(@Url String url,@HeaderMap Map<String,String> headers);

    @POST()
    Call<String> executePost( @Url String url,@HeaderMap Map<String,String> headers);

    @POST()
    @FormUrlEncoded
    Call<String> executePost( @Url String url, @FieldMap Map<String, String> maps,@HeaderMap Map<String,String> headers);
}
