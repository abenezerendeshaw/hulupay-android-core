package com.hululabs.hulupay_android_core_lb;

import androidx.annotation.NonNull;

import java.util.List;

public interface RevealServiceProviderCallbacks {
    void onSuccess(@NonNull List<ServiceProvider> serviceProvider);

    void onError(@NonNull Throwable throwable);
}
