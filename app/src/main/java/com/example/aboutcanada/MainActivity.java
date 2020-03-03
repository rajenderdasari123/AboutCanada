package com.example.aboutcanada;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.aboutcanada.databinding.ActivityMainBinding;

import static com.example.aboutcanada.BR.mainViewModel;

public class MainActivity extends AppCompatActivity {
  private MainViewModel mMainViewModel;
  private SwipeRefreshLayout mSwipeRefreshLayout;
  private ActivityMainBinding mActivityMainBinding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mMainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    mActivityMainBinding.setVariable(mainViewModel, mMainViewModel);
    mActivityMainBinding.setLifecycleOwner(this);
    mActivityMainBinding.executePendingBindings();
    mSwipeRefreshLayout = mActivityMainBinding.swipeRefresh;
    mMainViewModel.setAboutCanadaAdapter();
    mSwipeRefreshLayout.setOnRefreshListener(() -> mMainViewModel.getData());
    initObservers();
  }

  /**
   * This method initializes the Observers.
   */
  private void initObservers() {
    mMainViewModel.getHideSwipeRefreshLiveData().observe(this, this::hideSwipeRefresh);
    mMainViewModel.getErrorMutableLiveData().observe(this, this::handleErrors);
    mMainViewModel.getNetworkErrorMutableLiveData().observe(this, this::networkError);
  }

  /**
   * This method handle NetworkError Call.
   *
   * @param aBoolean {@link Boolean}.
   */
  private void networkError(Boolean aBoolean) {
    if (aBoolean) {
      mActivityMainBinding.tvNoNetwork.setVisibility(View.VISIBLE);
      mActivityMainBinding.canadaList.setVisibility(View.GONE);
    } else {
      mActivityMainBinding.tvNoNetwork.setVisibility(View.GONE);
      mActivityMainBinding.canadaList.setVisibility(View.VISIBLE);
    }
  }

  /**
   * This method handle Api Call Errors.
   *
   * @param throwable {@link Throwable}.
   */
  private void handleErrors(Throwable throwable) {
    Toast.makeText(this, throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
  }

  /**
   * This method is used to hide the Swipe Refresh once after fetching all the data.
   *
   * @param aBoolean {@link Boolean}.
   */
  private void hideSwipeRefresh(Boolean aBoolean) {
    if (aBoolean) {
      mSwipeRefreshLayout.setRefreshing(false);
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    mMainViewModel.onCleared();
    mMainViewModel.getHideSwipeRefreshLiveData().removeObservers(this);
  }
}
