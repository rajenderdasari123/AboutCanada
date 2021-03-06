package com.example.aboutcanada;

import android.app.Application;

import com.example.aboutcanada.adapter.AboutCanadaAdapter;
import com.example.aboutcanada.managar.CloudManager;
import com.example.aboutcanada.model.AboutCanada;
import com.example.aboutcanada.model.AboutCanadaDetails;
import com.example.aboutcanada.utils.NetworkUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;

public class MainViewModel extends AndroidViewModel {
  private final CloudManager mCloudManager;
  private List<AboutCanadaDetails> mAboutCanadaDetailsList;
  private Application mApplication;
  private Disposable mDisposable;
  private AboutCanadaAdapter mAboutCanadaAdapter;
  private MutableLiveData mTitleLD;
  private MutableLiveData<Boolean> mHideSwipeRefreshLiveData;
  private MutableLiveData<Throwable> mErrorMutableLiveData;
  private MutableLiveData<Boolean> mNetworkErrorMutableLiveData;

  public MainViewModel(@NonNull Application application) {
    super(application);
    mCloudManager = CloudManager.getInstance(application);
    mAboutCanadaAdapter = new AboutCanadaAdapter(this);
    mApplication = application;
    mTitleLD = new MutableLiveData();
    mHideSwipeRefreshLiveData = new MutableLiveData<>();
    mErrorMutableLiveData = new MutableLiveData<>();
    mNetworkErrorMutableLiveData = new MutableLiveData<>();
    mAboutCanadaDetailsList = new ArrayList<>();
    getData();
  }

  /**
   * This method Fetches data from api call and populate to View.
   */
  protected void getData() {
    if (NetworkUtil.isNetworkConnected(mApplication)) {
      mNetworkErrorMutableLiveData.postValue(false);
      mDisposable = mCloudManager.getData().subscribeWith(new DisposableSingleObserver<AboutCanada>() {
        @Override
        public void onSuccess(AboutCanada aboutCanada) {
          mHideSwipeRefreshLiveData.setValue(true);
          mTitleLD.setValue(aboutCanada.getTitle());
          mAboutCanadaDetailsList = aboutCanada.getRows();
          setAboutCanadaAdapter();
        }

        @Override
        public void onError(Throwable e) {
          mHideSwipeRefreshLiveData.setValue(true);
          mErrorMutableLiveData.postValue(e);
        }
      });
    } else {
      mHideSwipeRefreshLiveData.setValue(true);
      mNetworkErrorMutableLiveData.postValue(true);
    }
  }

  public void setAboutCanadaAdapter() {
    mAboutCanadaAdapter.setAboutCanadaList(mAboutCanadaDetailsList);
    mAboutCanadaAdapter.notifyDataSetChanged();
  }

  /**
   * gets the values from the api call and populates to a list item with {@link AboutCanadaDetails} at the
   * position mentioned.
   *
   * @param position {@link Integer}
   * @return {@link AboutCanadaDetails}.
   */
  public AboutCanadaDetails getAboutCanadaDetails(Integer position) {
    return mAboutCanadaDetailsList.get(position);
  }

  public MutableLiveData getTitleLD() {
    return mTitleLD;
  }

  public AboutCanadaAdapter getAboutCanadaAdapter() {
    return mAboutCanadaAdapter;
  }

  public MutableLiveData<Boolean> getHideSwipeRefreshLiveData() {
    return mHideSwipeRefreshLiveData;
  }

  public MutableLiveData<Throwable> getErrorMutableLiveData() {
    return mErrorMutableLiveData;
  }

  public MutableLiveData<Boolean> getNetworkErrorMutableLiveData() {
    return mNetworkErrorMutableLiveData;
  }

  /**
   * Disposes all the disposables on Destroy of activity.
   */
  @Override
  protected void onCleared() {
    super.onCleared();
    if (mDisposable != null) {
      mDisposable.dispose();
    }
  }

  @VisibleForTesting
  public AboutCanadaAdapter getAdapter() {
    return mAboutCanadaAdapter;
  }

  @VisibleForTesting
  public void setAdapterList(List<AboutCanadaDetails> detailsList) {
    mAboutCanadaDetailsList = detailsList;
  }
}
