package com.hululabs.hulupay_android_core_lb;

import androidx.annotation.NonNull;

import java.util.List;

public interface RevealSingleServiceProviderCallbacks {
    void onSuccess(@NonNull ServiceProvider serviceProvider);

    void onError(@NonNull Throwable throwable);
}
