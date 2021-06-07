package com.fang.net;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 *网络请求Client的创建类
 */
public class NetClient {

    private static volatile NetClient mInstance;
    private Retrofit mRetrofit;

    public static NetClient getInstance(){
        if (mInstance == null){
            synchronized (NetClient.class){
                if (mInstance == null){
                    mInstance = new NetClient();
                }
            }
        }
        return mInstance;
    }

    private NetClient(){
        initRetrofit();
    }

    /**
     * 初始化Retrofit
     */
    private void initRetrofit() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(NetClientConfig.TIME_OUT, NetClientConfig.TIME_UNIT);
        builder.readTimeout(NetClientConfig.TIME_OUT, NetClientConfig.TIME_UNIT);
        builder.writeTimeout(NetClientConfig.TIME_OUT, NetClientConfig.TIME_UNIT);
        builder.sslSocketFactory(SSLTrustAllSocketFactory.createSSLSocketFactory());
        builder.hostnameVerifier(new SSLTrustAllSocketFactory.TrustAllHostnameVerifier());
        OkHttpClient client = builder.build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(NetClientConfig.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
    }
    /**
     * 创建API
     */
    public <T> T create(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }
    /**
     * 创建BaseApi
     */
    public BaseInterface create() {
        return mRetrofit.create(BaseInterface.class);
    }
}
