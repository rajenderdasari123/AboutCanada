package com.example.aboutcanada.service;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CloudService {
  private static Retrofit retrofit = null;
  private static final String BASE_URL = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/";

  private CloudService() {
  }

  public static synchronized Retrofit getClient() {
    if (retrofit == null) {
      retrofit = new Retrofit.Builder()
          .baseUrl(BASE_URL)
          .addConverterFactory(GsonConverterFactory.create())
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
          .build();

    }
    return retrofit;
  }
}
