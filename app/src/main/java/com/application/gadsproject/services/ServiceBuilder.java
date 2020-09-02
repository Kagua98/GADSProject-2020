package com.application.gadsproject.services;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceBuilder {
    private static final String URL = "https://gadsapi.herokuapp.com";
    private static final String GoogleDriveURL = "https://docs.google.com/forms/d/e/";

    public static HttpLoggingInterceptor logger =
            new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    private static OkHttpClient.Builder okHttp =
            new OkHttpClient.Builder().addInterceptor(logger);

    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp.build());

    private static Retrofit retrofit = builder.build();

    public static <S> S buildService(Class<S> serviceType){
        return retrofit.create(serviceType);
    }

    private static Retrofit.Builder builder1 = new Retrofit.Builder().baseUrl(GoogleDriveURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp.build());

    private static Retrofit retrofit1 = builder1.build();

    public static <S> S submitToDrive(Class<S> serviceType){
        return retrofit1.create(serviceType);
    }

}
