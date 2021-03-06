package com.example.aboutcanada.managar;

import android.content.Context;

import com.example.aboutcanada.interfaces.CloudApi;
import com.example.aboutcanada.model.AboutCanada;
import com.example.aboutcanada.service.CloudService;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CloudManager {
  private static CloudApi mCloudApi;
  private static CloudManager mCloudManager = null;

  private CloudManager() {
  }

  public static CloudManager getInstance(Context context) {
    if (mCloudManager == null) {
      mCloudManager = new CloudManager();
      if (context != null) {
        mCloudApi = CloudService.getClient().create(CloudApi.class);
      }
    }
    return mCloudManager;
  }

  /**
   * Api Call to getData From the Cloud.
   * @return {@link Single<AboutCanada>}
   */
  public Single<AboutCanada> getData() {
    return mCloudApi.getString()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }
}