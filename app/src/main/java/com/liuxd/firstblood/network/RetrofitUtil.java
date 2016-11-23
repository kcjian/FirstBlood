package com.liuxd.firstblood.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.liuxd.firstblood.MyApp;
import com.liuxd.firstblood.constant.Constant;
import com.liuxd.firstblood.util.LogUtil;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Liuxd on 2016/9/23 11:21.
 * 网络请求工具类
 * <p>
 * 封装retrofit+okHttp
 */

public  class RetrofitUtil {
    private ApiService mApiService;

    private static final int CONNECT_TIMEOUT = 10;
    private static final int READ_TIMEOUT = 20;
    private static final File CACHE_PATH = MyApp.getInstance().getCacheDir();
    private static final int MAX_CACHE_SIZE = 20 * 1024 * 1024;

    public RetrofitUtil() {
        LogUtil.d("初始化retrofit:", CACHE_PATH.getAbsolutePath());
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .cache(new Cache(CACHE_PATH, MAX_CACHE_SIZE));
        OkHttpClient okHttpClient = builder.build();
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Constant.BASE_URL)
                .build();
        mApiService = retrofit.create(ApiService.class);
    }

    public ApiService getApiService() {
        return mApiService;
    }
}
