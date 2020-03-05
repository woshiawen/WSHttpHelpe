package com.wshttp.config;




import android.util.ArrayMap;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;



/**
 *之后的所有都需要继承此接口
 */
public interface WSHttpApi {

    @GET
    Observable<Object> get(@Url String url, @QueryMap ArrayMap<String,String> arrayMap);



    @FormUrlEncoded
    @POST
    Observable<Object> post(
            @Url String url,
            @FieldMap ArrayMap<String,String> fieldMap
    );


    @POST
    Observable<Object> uploadFileWithMultipartBody(
            @Url String url,
            @Body MultipartBody multipartBody);



    @Multipart
    @POST
    Observable<Object> uploadFileWithParts(
            @Url String url,
            @Body List<MultipartBody.Part> parts);


    @GET
    Observable<ResponseBody> downLoadFile(@Url String url);


}


