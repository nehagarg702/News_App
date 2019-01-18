package com.dell.example.newsApp;
/****
 * Class for caching the data for four weeks
 */

import android.support.annotation.NonNull;
import com.dell.example.newsApp.UtilityMethods;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class OfflineResponseCacheInterceptor implements Interceptor{
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        if (!UtilityMethods.isNetworkAvailable()) {
            request = request.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + 2419200)
                    .build();
        }
        return chain.proceed(request);
    }

}
