package gg.googlrewardads.retorfit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Go Goal on 3/4/2017.
 */
public interface interfacec {


    @FormUrlEncoded
    @POST("my/userticket/info")
    Call<String> getuseridanduserticket(@Field("User-Agent") String name,@Field("api") String api);


    @FormUrlEncoded
    @POST("my/userticket/sent")
    Call<String> sent(@Field("User-Agent") String name,@Field("userid") String api);


    @FormUrlEncoded
    @POST("my/userticket/faosdonoaphin2")
    Call<String> faosdonoaphin(@Field("User-Agent") String name,@Field("userid") String api);





}
