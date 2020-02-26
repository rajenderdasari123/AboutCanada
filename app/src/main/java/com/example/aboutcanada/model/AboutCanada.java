package com.example.aboutcanada.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AboutCanada {

  @SerializedName("title")
  @Expose
  private String title;
  @SerializedName("rows")
  @Expose
  private List<AboutCanadaDetails> rows = null;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<AboutCanadaDetails> getRows() {
    return rows;
  }

  public void setRows(List<AboutCanadaDetails> rows) {
    this.rows = rows;
  }

}

