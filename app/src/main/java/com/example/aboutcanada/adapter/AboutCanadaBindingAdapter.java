package com.example.aboutcanada.adapter;

import android.widget.ImageView;

import com.example.aboutcanada.R;
import com.squareup.picasso.Picasso;

import androidx.databinding.BindingAdapter;

public class AboutCanadaBindingAdapter {

  private AboutCanadaBindingAdapter() {
  }

  @BindingAdapter({"app:imageurl"})
  public static void loadImage(ImageView imageView, String imageurl) {
    if (imageurl != null && !imageurl.isEmpty()) {
      Picasso.with(imageView.getContext())
          .load(imageurl)
          .placeholder(R.drawable.loading)
          .into(imageView);
    }
  }
}
