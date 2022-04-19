package com.hululabs.hulupay_android_core_lb;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface HuluPayApi {

    @FormUrlEncoded
    @POST("login/access-token")
    Call<AccessToken> init(
            @Field("username") String username,
            @Field("password") String password
    );

    @GET("serviceproviders/")
    Call<List<ServiceProvider>> getServiceProviders (@Header("Authorization" ) String token);

    @GET("serviceproviders/{id}")
    Call<ServiceProvider> getSingleServiceProviders(
            @Header("Authorization" ) String token ,
            @Path("id") int id
            );
}
