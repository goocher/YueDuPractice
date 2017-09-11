package com.xes.yuedupractice.net;

import com.xes.yuedupractice.BuildConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * <pre>
 *     author : gooch
 *     e-mail : zhaoguangchao@100tal.com
 *     time   : 2017/09/11
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class ServiceApi {
    public static final int TIMEOUT = 60;
    private static ServiceApi sServiceApi;
    private static final String BASE_URL = "";
    private static ApiService sApiService;

    private ServiceApi() {

    }

    public static ServiceApi getInstance() {
        if (sServiceApi == null) {
            synchronized (ServiceApi.class) {
                if (sServiceApi == null) {
                    sServiceApi = new ServiceApi();
                }
            }
        }
        return sServiceApi;
    }

    static {
        initRetrofit();
    }

    private static void initRetrofit() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        }
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        Request result = request.newBuilder()
                                .addHeader("sys", "ssss")
                                .removeHeader("")
                                .build();
                        return chain.proceed(result).newBuilder()
                                .build();
                    }
                }).addNetworkInterceptor(httpLoggingInterceptor)
                .retryOnConnectionFailure(false)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();
        sApiService = retrofit.create(ApiService.class);
    }
}
