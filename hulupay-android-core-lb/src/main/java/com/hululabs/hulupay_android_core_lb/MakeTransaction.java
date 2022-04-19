package com.hululabs.hulupay_android_core_lb;

import static io.sentry.android.AndroidSentryClientFactory.TAG;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.hover.sdk.api.Hover;
import com.hover.sdk.api.HoverParameters;
import com.hover.sdk.*;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MakeTransaction {

    HuluPayApi huluPayApi = RetrofitInstance.mInstance().create(HuluPayApi.class);



    public  Intent MakeTransaction(Context context , String receiver, String amount , String Reason , int id ,String Appcode , String token) {

                HashMap<String, String> Map=new HashMap<String, String>();
                Map.put("Reciver", receiver);
                Map.put("amount" , amount);
                Map.put("Reason", Reason);
                Map.put("id" , String.valueOf(id));
                Map.put("Appcode" , Appcode);
                Map.put("token", token);


            Call<ServiceProvider> serviceProviderCall = huluPayApi.getSingleServiceProviders("Bearer " + token , id);
            serviceProviderCall.enqueue(new Callback<ServiceProvider>() {
                @Override
                public void onResponse(Call<ServiceProvider> call, Response<ServiceProvider> response) {

                        if (!response.isSuccessful()){
                            Toast.makeText(context, "Error" + response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        }

                        ServiceProvider provider = response.body();
                        String content = "";
                        content += "\n"+"activation " + provider.isIs_active() + "\n"
                        + "Bank Name: " + provider.getName() + "\n" +
                        "institution: " + provider.getInstitution();


                        if(provider.isIs_active() == true){

                               String x = Map.get("Reciver");

                            Toast.makeText(context, "Message : " + content, Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "onResponse: " + content);
                            Log.d(TAG, "onResponse Map : " + x);


                        }


                }

                @Override
                public void onFailure(Call<ServiceProvider> call, Throwable t) {
                    Toast.makeText(context, "OnFailure" + t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });




        Intent i = new HoverParameters.Builder(context)
                .request("15a8a89b")
                .extra("accountNumber", receiver)
                .extra("amount", amount)
                .extra("reason", Reason)
                .buildIntent();

        return i;




    }



}