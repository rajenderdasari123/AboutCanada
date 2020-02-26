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

/**
 * The test class for {@link AboutCanadaDetails}
 */
@Config(sdk = Build.VERSION_CODES.O)
@RunWith(RobolectricTestRunner.class)
public class AboutCanadaDetailsTest {

  private AboutCanadaDetails mAboutCanadaDetails;

  @Before
  public void setUp() {
    mAboutCanadaDetails = new AboutCanadaDetails();
  }

  @Test
  public void testSetAndGetTitle() {
    String testTitle = "Flag";
    mAboutCanadaDetails.setTitle(testTitle);
    Assert.assertEquals(testTitle, mAboutCanadaDetails.getTitle());
  }

  @Test
  public void testSetAndGetDescription() {
    String testDescription = "Warmer then you might think";
    mAboutCanadaDetails.setDescription(testDescription);
    Assert.assertEquals(testDescription, mAboutCanadaDetails.getDescription());
  }

  @Test
  public void testSetAndGetImageReference() {
    Object testImageRef = Image.Plane.class;
    mAboutCanadaDetails.setImageHref(testImageRef);
    Assert.assertEquals(testImageRef, mAboutCanadaDetails.getImageHref());

  }

  @After
  public void tearDown() {
    mAboutCanadaDetails = null;
  }
}
