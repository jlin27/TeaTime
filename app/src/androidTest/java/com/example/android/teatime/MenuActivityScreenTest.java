package com.example.android.teatime;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by jessicalin on 1/6/17.
 */

@RunWith(AndroidJUnit4.class)
public class MenuActivityScreenTest {

    // Using the ActivityTestRule, we tell the Runner to launch the activity before any tests
    // and tear it down after we're finished
    @Rule
    public ActivityTestRule<MenuActivity> mMenuActivityTestRule =
            new ActivityTestRule<MenuActivity>(MenuActivity.class);


    //TODO: Finish this up with onData from here:
    // https://google.github.io/android-testing-support-library/docs/espresso/basics/index.html

    @Test
    public void clickGridViewItem_openOrderActivity() throws Exception{

    }



}
