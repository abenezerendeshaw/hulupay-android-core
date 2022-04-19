package com.hululabs.hulupay_android_core;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hululabs.hulupay_android_core_lb.AccessToken;
import com.hululabs.hulupay_android_core_lb.HuluPayApi;
import com.hululabs.hulupay_android_core_lb.MakeTransaction;
import com.hululabs.hulupay_android_core_lb.RetrofitInstance;
import com.hululabs.hulupay_android_core_lb.RevealAccessTokenCallbacks;
import com.hululabs.hulupay_android_core_lb.RevealServiceProviderCallbacks;
import com.hululabs.hulupay_android_core_lb.ServiceProvider;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    TextView textview;
    Button token_btn,provider_btn;
    String authToken;
    ActivityResultLauncher activityResultLauncher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       // HuluPayApi huluPayApi = retrofit.create(HuluPayApi.class);
        HuluPayApi huluPayApi = RetrofitInstance.mInstance().create(HuluPayApi.class);



        Button button= (Button) findViewById(R.id.action_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MakeTransaction makeTransaction = new MakeTransaction();
                Intent i = makeTransaction.MakeTransaction(getApplicationContext(),"1000254571303","5" ,"Testing library",1,"101" ,authToken);
                startActivityForResult(i,0);
            }
        });


        textview = findViewById(R.id.textView);

        token_btn = findViewById(R.id.token);

        token_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getToken(new RevealAccessTokenCallbacks() {
                    @Override
                    public void onSuccess(@NonNull @NotNull AccessToken accessToken) {
//                        Log.d("MYAPP", "I shouldn't be here");
                        authToken = accessToken.getAccess_token();
                        textview.setText("Response Auth : " + authToken);
                    }

                    @Override
                    public void onError(@NonNull @org.jetbrains.annotations.NotNull Throwable throwable) {
                        textview.setText("Response Error : " + throwable.getMessage());

                    }
                }, RetrofitInstance.mInstance(), huluPayApi);
            }
        });

        provider_btn = findViewById(R.id.providers);

        provider_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getServiceProviders(new RevealServiceProviderCallbacks() {
                    @Override
                    public void onSuccess(@NonNull @NotNull List<ServiceProvider> serviceProvider) {
                        textview.setText("");
                        for (ServiceProvider aserviceprovider : serviceProvider) {
                            String provider = "Name : " + aserviceprovider.getName() + "\n" +
                                              "Institution : " + aserviceprovider.getInstitution() + "\n";
                            textview.append(provider);
                        };
                    }
                    @Override
                    public void onError(@NonNull @NotNull Throwable throwable) {
                        textview.setText("Response Error : " + throwable.getMessage());
                    }
                }, RetrofitInstance.mInstance(), huluPayApi);

            }
        });
    }

    private void getToken(@Nullable RevealAccessTokenCallbacks callbacks, Retrofit retrofit, HuluPayApi huluPayApi){

        Call<AccessToken> call = huluPayApi.init("admin@hulupay.com", "hulu-pay.pass");
        call.enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                if(response.isSuccessful()){
                    if (callbacks != null)
                        callbacks.onSuccess(response.body());
                }
                else{
                    Log.d("Error", "Something is wrong! Error msg:" + response.message());
//                    callbacks.onSuccess(response.body());
                }
            }
            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {
                if (callbacks != null)
                    callbacks.onError(t);
            }
        });
    }


    private void getServiceProviders(@Nullable RevealServiceProviderCallbacks callbacks, Retrofit retrofit, HuluPayApi huluPayApi){

        Call<List<ServiceProvider>> call= huluPayApi.getServiceProviders("Bearer " + authToken);
        call.enqueue(new Callback<List<ServiceProvider>>() {
            @Override
            public void onResponse(Call<List<ServiceProvider>> call, Response<List<ServiceProvider>> response) {
                if(response.isSuccessful()){
                    if (callbacks != null)
                        callbacks.onSuccess(response.body());
                }
                else{
                    Log.d("NETWORKING", "Something is wrong! Error msg:" + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<ServiceProvider>> call, Throwable t) {
                if (callbacks != null)
                    callbacks.onError(t);
            }
        });


    }









}