package com.niupule.niuapp.retrofit;

import com.niupule.niuapp.data.detail.LoginData;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Coder: niupuyue (牛谱乐)
 * Date: 2018/9/3
 * Time: 20:45
 * Desc:
 * Version:
 */
public interface RetrofitService {

    @FormUrlEncoded
    @POST(Api.LOGIN)
    Observable<LoginData> login(@Field("username") String username,@Field("password") String password);

    @FormUrlEncoded
    @POST(Api.REGISTER)
    Observable<LoginData> register(@Field("username") String username,@Field("password") String password,@Field("repassword") String repassword);

}