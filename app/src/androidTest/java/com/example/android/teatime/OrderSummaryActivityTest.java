package com.example.android.teatime;

import android.app.Activity;
import android.content.Intent;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.app.Instrumentation.ActivityResult;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class OrderSummaryActivityTest {


    /**
     *
     * This test demonstrates Espresso Intents which allows intent stubbing and validation.
     *
     * A JUnit {@link Rule @Rule} initializes and release Espresso Intents before and after each
     * test run.
     * <p>
     * Rules are interceptors which are executed for each test method and will run before
     * any of your setup code in the {@link Before @Before} method.
     * <p>
     * This rule is based on {@link ActivityTestRule} and will create and launch of the activity
     * for you and also expose the activity under test.
     */

    // TODO remove these extras and reference the actual ones for consistency
    public final static String EXTRA_SIZE = "com.example.android.teatime.EXTRA_SIZE";
    public static final String TEA_SIZE = "Small";

    @Rule
    public IntentsTestRule<OrderSummaryActivity> mActivityRule = new IntentsTestRule<>(
            OrderSummaryActivity.class);

    @Before
    public void stubOrderIntent() {
        ActivityResult result = createOrderSummaryActivityResultStub();

        // Stub the Intent. When an intent is sent to OrderSummaryActivity,
        // this tells Espresso to respond with the ActivityResult we just created
        intending(toPackage("com.example.android.teatime")).respondWith(result);
    }


    private ActivityResult createOrderSummaryActivityResultStub() {

        // Create the Intent that will be used in the Intent Stub
        Intent resultData = new Intent();
        resultData.putExtra(EXTRA_SIZE, TEA_SIZE);

        // Create the ActivityResult with the Intent.
        return new ActivityResult(Activity.RESULT_OK, resultData);


    }

    @Test
    public void placeOrder_checkSummary() {

        // Check that the order summary displays the selected customization
        onView(withId(R.id.summary_tea_size)).check(matches(withText(TEA_SIZE)));

    }



}
