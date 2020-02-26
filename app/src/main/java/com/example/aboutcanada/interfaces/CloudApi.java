package com.example.aboutcanada.interfaces;

import com.example.aboutcanada.model.AboutCanada;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface CloudApi {

  @GET("facts.json")
  Single<AboutCanada> getString();
}

