package com.hululabs.hulupay_android_core_lb;

import androidx.annotation.NonNull;

public interface RevealAccessTokenCallbacks {
    void onSuccess(@NonNull AccessToken accessToken);

    void onError(@NonNull Throwable throwable);
}
