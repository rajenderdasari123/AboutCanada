package com.example.aboutcanada.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.aboutcanada.R;

import androidx.databinding.BindingAdapter;

public class AboutCanadaBindingAdapter {

  private AboutCanadaBindingAdapter() {
  }

  @BindingAdapter({"app:imageurl"})
  public static void loadImage(ImageView imageView, String imageurl) {
    if (imageurl != null && !imageurl.isEmpty()) {
      Glide.with(imageView.getContext())
          .load(imageurl).placeholder(R.drawable.ic_launcher).into(imageView);
    }
  }
}
