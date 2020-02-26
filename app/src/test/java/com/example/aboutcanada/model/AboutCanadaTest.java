package com.example.aboutcanada.model;

import android.media.Image;
import android.os.Build;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

/**
 * The test class for {@link AboutCanada}.
 */
@Config(sdk = Build.VERSION_CODES.O)
@RunWith(RobolectricTestRunner.class)
public class AboutCanadaTest {
  private AboutCanada mAboutCanada;
  private AboutCanadaDetails mAboutCanadaDetails;

  @Before
  public void setUp() {
    mAboutCanada = new AboutCanada();
    mAboutCanadaDetails = new AboutCanadaDetails();
  }

  @Test
  public void testSetAndGetTitle() {
    String title = "About Canada";
    mAboutCanada.setTitle(title);
    Assert.assertEquals(title, mAboutCanada.getTitle());
  }

  @Test
  public void testSetAndGetRow() {
    mAboutCanada.setRows(getDetails());
    Assert.assertEquals(2, mAboutCanada.getRows().size());
  }

  private List<AboutCanadaDetails> getDetails() {
    List<AboutCanadaDetails> detailsList = new ArrayList<>();
    mAboutCanadaDetails.setTitle("Flag");
    mAboutCanadaDetails.setDescription("Warmer then you might think");
    mAboutCanadaDetails.setImageHref(Image.Plane.class);

    detailsList.add(mAboutCanadaDetails);

    mAboutCanadaDetails.setTitle("Public Shame");
    mAboutCanadaDetails.setDescription("Sadly it's true.");
    mAboutCanadaDetails.setImageHref(Image.Plane.class);

    detailsList.add(mAboutCanadaDetails);
    return detailsList;
  }

  @After
  public void tearDown() {
    mAboutCanada = null;
    mAboutCanadaDetails = null;
  }
}
