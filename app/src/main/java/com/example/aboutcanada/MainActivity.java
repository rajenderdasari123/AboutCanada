package com.example.aboutcanada;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.Toast;

import com.example.aboutcanada.databinding.ActivityMainBinding;

import static com.example.aboutcanada.BR.mainViewModel;

public class MainActivity extends AppCompatActivity {
  private MainViewModel mMainViewModel;
  private SwipeRefreshLayout mSwipeRefreshLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mMainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    activityMainBinding.setVariable(mainViewModel, mMainViewModel);
    activityMainBinding.setLifecycleOwner(this);
    activityMainBinding.executePendingBindings();
    mSwipeRefreshLayout = activityMainBinding.swipeRefresh;
    mMainViewModel.getData();
    mSwipeRefreshLayout.setOnRefreshListener(() -> mMainViewModel.getData());
    initObservers();
  }

  /**
   * This method initializes the Observers.
   */
  private void initObservers() {
    mMainViewModel.getHideSwipeRefreshLiveData().observe(this, this::hideSwipeRefresh);
    mMainViewModel.getErrorMutableLiveData().observe(this, this::handleErrors);
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
