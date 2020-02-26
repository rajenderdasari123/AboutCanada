package com.example.aboutcanada.adapter;

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

/**
 * Adapter to populate aboutcanada information list
 */
public class AboutCanadaAdapter extends RecyclerView.Adapter<AboutCanadaAdapter.ItemViewHolder> {

  private List<AboutCanadaDetails> mAboutCanadaDetailsList;
  private MainViewModel mMainViewModel;

  public AboutCanadaAdapter(MainViewModel mainViewModel) {
    mMainViewModel = mainViewModel;
  }

  @NonNull
  @Override
  public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
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
    mAboutCanadaDetailsList = aboutCanadaList;
  }

  /**
   * Item view holder sets the details for its view
   */
  public class ItemViewHolder extends RecyclerView.ViewHolder {
    final ListItemBinding listItemBinding;

    void bind(MainViewModel mainViewModel, Integer position) {
      listItemBinding.setMainViewModel(mainViewModel);
      listItemBinding.setPosition(position);
      listItemBinding.executePendingBindings();
    }

    /**
     * @param listItemBinding {@Link listItemBinding}.
     */
    public ItemViewHolder(ListItemBinding listItemBinding) {
      super(listItemBinding.getRoot());
      this.listItemBinding = listItemBinding;
    }
  }

}
