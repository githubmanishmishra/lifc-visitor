package com.laxmi.lifcvisitors.retrofitservices;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClientPincode {

    public static final String BASE_URL = "https://api.postalpincode.in/pincode/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit==null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(
                            chain -> {
                                Request original = chain.request();
                                // Request customization: add request headers
                                Request.Builder requestBuilder =original.newBuilder().
                                        method(original.method(), original.body());
                                Request request = requestBuilder.build();
                                return chain.proceed(request);
                            })
                    .addInterceptor(interceptor).connectTimeout(60, TimeUnit.SECONDS).
                    readTimeout(60, TimeUnit.SECONDS).build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }
}
