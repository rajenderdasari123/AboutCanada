package com.example.aboutcanada.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

import androidx.databinding.BindingAdapter;

public class AboutCanadaBindingAdapter {

  private AboutCanadaBindingAdapter() {
  }

  @BindingAdapter({"app:imageurl"})
  public static void loadImage(ImageView imageView, String imageurl) {
    if (imageurl != null && !imageurl.isEmpty()) {
      Glide.with(imageView.getContext())
          .load(imageurl).into(imageView);
    }
  }
}
