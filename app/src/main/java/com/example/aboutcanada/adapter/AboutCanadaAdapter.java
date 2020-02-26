package com.example.aboutcanada.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.aboutcanada.MainViewModel;
import com.example.aboutcanada.R;
import com.example.aboutcanada.databinding.ListItemBinding;
import com.example.aboutcanada.model.AboutCanadaDetails;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class AboutCanadaAdapter extends RecyclerView.Adapter<AboutCanadaAdapter.ItemViewHolder>{

  private List<AboutCanadaDetails> mAboutCanadaDetailsList;
  private MainViewModel mMainViewModel;
  private Context mContext;

  public AboutCanadaAdapter(MainViewModel mainViewModel, Context context) {
    mMainViewModel=mainViewModel;
    mContext=context;
  }

  @NonNull
  @Override
  public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater layoutInflater =LayoutInflater.from(parent.getContext());
    ListItemBinding listItemBinding = DataBindingUtil.inflate(layoutInflater,
        R.layout.list_item, parent, false);
    return new AboutCanadaAdapter.ItemViewHolder(listItemBinding);
  }

  @Override
  public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
    holder.bind(mMainViewModel, position);
  }

  @Override
  public int getItemCount() {
    return mAboutCanadaDetailsList != null ? mAboutCanadaDetailsList.size() : 0;
  }

  public void setAboutCanadaList(List<AboutCanadaDetails> aboutCanadaList) {
    mAboutCanadaDetailsList=aboutCanadaList;
  }

  public class ItemViewHolder extends RecyclerView.ViewHolder {
    final ListItemBinding listItemBinding;

    void bind(MainViewModel mainViewModel, Integer position) {
      listItemBinding.setMainViewModel(mainViewModel);
      listItemBinding.setPosition(position);
      listItemBinding.executePendingBindings();
    }
    public ItemViewHolder(ListItemBinding activityMainBinding) {
      super(activityMainBinding.getRoot());
      this.listItemBinding = activityMainBinding;
    }
  }

}
