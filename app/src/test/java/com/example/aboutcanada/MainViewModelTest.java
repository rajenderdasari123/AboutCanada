package com.example.aboutcanada;

import android.app.Application;
import android.content.Context;
import android.os.Build;

import com.example.aboutcanada.adapter.AboutCanadaAdapter;
import com.example.aboutcanada.model.AboutCanadaDetails;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import androidx.test.platform.app.InstrumentationRegistry;

/**
 * The test class for {@link MainViewModel}
 */
@Config(sdk = Build.VERSION_CODES.O)
@RunWith(RobolectricTestRunner.class)
public class MainViewModelTest {
  private MainViewModel mMainViewModel;
  private AboutCanadaAdapter mAboutCanadaAdapter;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    Context context = InstrumentationRegistry.getInstrumentation().getContext();
    mMainViewModel = new MainViewModel((Application) context);
    mMainViewModel.setAdapterList(getDetailsList());
    mAboutCanadaAdapter = mMainViewModel.getAboutCanadaAdapter();
  }

  @Test
  public void testAdapter() {
    mMainViewModel.setAboutCanadaAdapter();
    Assert.assertEquals(2, mAboutCanadaAdapter.getItemCount());
  }

  private List<AboutCanadaDetails> getDetailsList() {
    List<AboutCanadaDetails> rows = new ArrayList<>();

    AboutCanadaDetails aboutCanadaDetails = new AboutCanadaDetails();
    aboutCanadaDetails.setTitle("Test Title");
    aboutCanadaDetails.setDescription("Test Description");
    rows.add(aboutCanadaDetails);

    aboutCanadaDetails.setTitle("Test Title1");
    aboutCanadaDetails.setDescription("Test Description1");
    rows.add(aboutCanadaDetails);

    return rows;
  }

  @After
  public void tearDown() {
    mMainViewModel = null;
  }
}