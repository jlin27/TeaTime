package com.example.android.teatime;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.runner.RunWith;

/**
 * Created by jessicalin on 1/6/17.
 */

@RunWith(AndroidJUnit4.class)
public class OpenOrderSummaryActivityScreenTest {

    /**
     * {@link IntentsTestRule} is an {@link ActivityTestRule} which inits and releases Espresso
     * Intents before and after each test run.
     *
     * <p>
     * Rules are interceptors which are executed for each test method and are important building
     * blocks of Junit tests.
     */
    @Rule
    public IntentsTestRule<OrderSummaryActivity> mAddNoteIntentsTestRule =
            new IntentsTestRule<>(OrderSummaryActivity.class);


   /** // Test to simply validate an outgoing intent
    @Test
    public void validateIntentSentToPackage() {
        // User action that results in OrderSummaryActivity being launched.
        user.clickOnView(system.getView(R.id.order_button));

        // Using a canned RecordedIntentMatcher to validate that an intent resolving
        // to the OrderSummaryActivity is opened.
        intended(toPackage("com.android.phone"));
    }
    **/
}

