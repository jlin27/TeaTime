package com.example.android.teatime;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class SpinnerSelectionTest {

    /**
     * {@link IntentsTestRule} is an {@link ActivityTestRule} which inits and releases Espresso
     * Intents before and after each test run.
     *
     * <p>
     * Rules are interceptors which are executed for each test method and are important building
     * blocks of Junit tests.
     */
    @Rule
    public IntentsTestRule<OrderActivity> mAddNoteIntentsTestRule =
            new IntentsTestRule<>(OrderActivity.class);


    public static final String TEA_SIZE = "Medium";
    public static final String MILK_TYPE = "Nonfat Milk";
    public static final String SUGAR_TYPE = "100% - Full Sweetness";


    /** Test to validate the outgoing intent that is sent from OrderActivity to OrderSummaryActivity
     *  when the user clicks the order button.
     */

    @Test
    public void selectSpinnerItem_SpinnerUpdates() {
        // Click on the tea size spinner
        onView(withId(R.id.tea_size_spinner)).perform(click());

        // Click on the second item in the list
        onData(allOf(is(instanceOf(String.class)), is(TEA_SIZE))).perform(click());

        // Check that the spinner updates with the selected size
        onView(withId(R.id.tea_size_spinner))
                .check(matches(withSpinnerText(containsString(TEA_SIZE))));

    }

    @Test
    public void placeOrder_ShowsInSummary() {
        // Click on the tea size spinner
        onView(withId(R.id.tea_size_spinner)).perform(click());
        // Click on the second item in the list
        onData(allOf(is(instanceOf(String.class)), is(TEA_SIZE))).perform(click());

        // Click on the milk type spinner
        onView(withId(R.id.milk_spinner)).perform(click());
        // Click on the second item in the list
        onData(allOf(is(instanceOf(String.class)), is(MILK_TYPE))).perform(click());

        // Click on the sugar type spinner
        onView(withId(R.id.sugar_spinner)).perform(click());
        // Click on the last item in the list
        onData(allOf(is(instanceOf(String.class)), is(SUGAR_TYPE))).perform(click());


        //TODO: Redo with intent stubbing

        // Check that the order summary displays the selected customizations
        onView(withId(R.id.summary_tea_size)).check(matches(withText(TEA_SIZE)));
        onView(withId(R.id.summary_milk_type)).check(matches(withText(MILK_TYPE)));
        onView(withId(R.id.summary_sugar_amount)).check(matches(withText(SUGAR_TYPE)));



    }




}

