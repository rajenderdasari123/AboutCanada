package com.example.aboutcanada;

import android.os.Build;

import com.example.aboutcanada.managar.CloudManager;
import com.example.aboutcanada.model.AboutCanada;
import com.example.aboutcanada.model.AboutCanadaDetails;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import io.reactivex.Single;

import static org.junit.Assert.assertEquals;

@Config(sdk = Build.VERSION_CODES.O)
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
  private MainActivity mMainActivity;
  private ActivityScenario<MainActivity> mMainActivityScenario;

  @Mock
  CloudManager mCloudManager;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    List<AboutCanada> aboutCanada = new ArrayList<>();
    AboutCanada canadadetails = new AboutCanada();
    canadadetails.setTitle("Beavers");
    List<AboutCanadaDetails> rows = new ArrayList<>();
    AboutCanadaDetails aboutCanadaDetails = new AboutCanadaDetails();
    aboutCanadaDetails.setTitle("Housing");
    aboutCanadaDetails.setDescription("Warmer then you might think");
    rows.add(aboutCanadaDetails);
    canadadetails.setRows(rows);
    aboutCanada.add(canadadetails);
    Mockito.doReturn(Single.just(aboutCanada)).when(mCloudManager).getData();
    mMainActivityScenario = ActivityScenario.launch(MainActivity.class);
    mMainActivityScenario.moveToState(Lifecycle.State.RESUMED);
    mMainActivityScenario.onActivity(activity -> mMainActivity = activity);
  }

  @Test
  public void testInstance() {
    Assert.assertNotNull(mMainActivity);
  }

  @Test
  public void testDestroy() {
    mMainActivityScenario.moveToState(Lifecycle.State.DESTROYED);
    assertEquals(Lifecycle.State.DESTROYED, mMainActivity.getLifecycle().getCurrentState());
  }

  @After
  public void tearDown() {
    mMainActivity = null;
    mMainActivityScenario = null;
  }

}
