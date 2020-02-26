package com.example.aboutcanada.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * This { NetworkUtil} class is a utility class for network
 */
public class NetworkUtil {

  private NetworkUtil() {

  }

  public static boolean isNetworkConnected(Context context) {
    if (context != null) {
      ConnectivityManager connectivityManager = (ConnectivityManager)
          context.getSystemService(Context.CONNECTIVITY_SERVICE);

      NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
      return networkInfo != null && networkInfo.isConnected();
    }
    return false;
  }
}
