package com.hululabs.hulupay_android_core_lb;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.hululabs.hulupay_android_core_lb.StaticAssets;


public class RetrofitInstance {

    public static Retrofit retrofit;


    public static Retrofit mInstance(){

        if (retrofit == null){


            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .build();

                 retrofit = new Retrofit.Builder()
                    .baseUrl(StaticAssets.baseurl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();



        }

        return retrofit;
    }
}
