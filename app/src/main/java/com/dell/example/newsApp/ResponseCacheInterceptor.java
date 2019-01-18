package com.dell.example.newsApp;

/*****
 * Class to load the cached data in a minute
 */

import android.support.annotation.NonNull;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Response;

public class ResponseCacheInterceptor implements Interceptor {
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        return originalResponse.newBuilder()
                .removeHeader("Pragma")
                .header("Cache-Control", "public, max-age=" + 60)
                .build();
    }
}
