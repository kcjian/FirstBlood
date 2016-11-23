package com.liuxd.firstblood.network;

import com.liuxd.firstblood.entity.HttpResult;
import com.liuxd.firstblood.entity.News;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Liuxd on 2016/11/21 14:27.
 */

public interface ApiService {
    //    @HTTP(path = "https://api.douban.com/v2/movie/top250", method = "GET")
//    Observable<MovieEntity> getMovie(@Query("start") int start, @Query("count") int count);
//
//    @GET("/version.php")
//    Observable<String> getVersion(@Query("pin") String pin);
//
//    @GET("/getdata.php")
//    Observable<LampData> getData(@Query("pin") String pin);
//
//    @POST("/putdata.php")
//    Observable<String> putData(@Query("pin") PutData putData);
    @GET("toutiao/index?key=1e79b54104857c22282f23672f4cae1f")
    Observable<HttpResult<News>> getNewsByType(@Query("type") String type);

}
