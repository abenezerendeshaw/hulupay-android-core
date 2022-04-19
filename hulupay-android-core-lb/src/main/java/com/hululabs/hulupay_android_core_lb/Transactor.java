package com.hululabs.hulupay_android_core_lb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.ActivityCompat;

import com.hover.sdk.api.Hover;
import com.hover.sdk.api.HoverParameters;

public class Transactor {
    public Context ctx;
//    private Hover transactor = new Hover;
    public Transactor(Context ctx) {
        this.ctx = ctx;
        Hover.initialize(ctx);
    }
//    app_code, serviceProvider_ID, amount, reason,  receiver, token
    public void MakeTransaction(String appCode, int serviceProviderID, float amount, String reason, String receiver, String token){
//        Get service provider with the ID and extract the action id, parameters for that provider
        String actionid = "";
        Intent i = new HoverParameters.Builder(this.ctx)
                .request("action_id")
                .extra("step_variable_name", "variable_value_as_string") // Only if your action has variables
                .buildIntent();
        ActivityCompat.startActivityForResult((Activity) this.ctx, i, 0, null);

    }


}
