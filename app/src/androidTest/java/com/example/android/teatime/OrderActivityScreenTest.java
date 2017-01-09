package com.example.android.teatime;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by jessicalin on 1/6/17.
 */

@RunWith(AndroidJUnit4.class)
public class OrderActivityScreenTest {

    // Using the ActivityTestRule, we tell the Runner to launch the activity before any tests
    // and tear it down after we're finished
    @Rule
    public ActivityTestRule<OrderActivity> mOrderActivityTestRule =
            new ActivityTestRule<OrderActivity>(OrderActivity.class);


    // Three parts to the test
    // (1) Find View
    // (2) Choose an action to perform
    // (3) Verify outcome of actions with view assertion
    @Test
    public void clickDecrementButton_showQuantity() throws Exception{
        // Click on the order button
        onView(withId(R.id.decrement_button))
                .perform(click());
        // Check if the order summary screen is displayed
        onView(withId(R.id.quantity_text_view))
                .check(matches(isDisplayed()));

    }



}

