package com.example.aboutcanada;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;

import static com.example.aboutcanada.BR.mainViewModel;

public class MainActivity extends AppCompatActivity {
  private MainViewModel mMainViewModel;
  private SwipeRefreshLayout mSwipeRefreshLayout;
  private ViewDataBinding mViewDataBinding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mMainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    mViewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    mViewDataBinding.setVariable(mainViewModel, mMainViewModel);
    mViewDataBinding.setLifecycleOwner(this);
    mViewDataBinding.executePendingBindings();
    mSwipeRefreshLayout = findViewById(R.id.swipe_refresh);
    mMainViewModel.getData();
    mSwipeRefreshLayout.setOnRefreshListener(() -> mMainViewModel.getData());
    mMainViewModel.getHideSwipeRefreshLiveData().observe(this,this::hideSwipeRefresh);
  }

  private void hideSwipeRefresh(Boolean aBoolean) {
    mSwipeRefreshLayout.setRefreshing(false);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    mMainViewModel.clearDisposals();
  }
}
