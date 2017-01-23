package com.example.android.teatime;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * This test demos a user clicking the increment button and verifying that it properly increases
 * the quantity displayed in the TextView by 1 and the total cost increases by $5.00.
 */

@RunWith(AndroidJUnit4.class)
public class OrderActivityBasicTest {

    // TODO add comment about ActivityTestRule
    @Rule
    public ActivityTestRule<OrderActivity> mActivityTestRule = new ActivityTestRule<>(OrderActivity.class);

    @Test
    public void orderActivityBasicTest() {
        //TODO Add initial to verify zero
        //TODO write decrement button test
        //TODO rename to be more specific to actions (e.g. incrementChangesQuantityAndCost)
        // Click on increment button
        onView((withId(R.id.increment_button)))
                .perform(click());

        // Verify that the increment button increases the quantity by 1
        onView(withId(R.id.quantity_text_view)).check(matches(withText("1")));

        // Verify that the increment button also increases the total cost to $5.00
        onView(withId(R.id.cost_text_view)).check(matches(withText("$5.00")));

    }
}
