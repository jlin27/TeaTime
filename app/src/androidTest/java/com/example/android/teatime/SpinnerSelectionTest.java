package com.example.android.teatime;

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
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

/**
 * Created by jessicalin on 1/10/17.
 */

@RunWith(AndroidJUnit4.class)
public class SpinnerSelectionTest {
    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(
            OrderActivity.class);

    @Test
    public void iterateSpinnerItems() {

        String[] myArray =
                mActivityRule.getActivity().getResources()
                        .getStringArray(R.array.tea_size_array);

        int size = myArray.length;
        for (int i=0; i<size; i++) {

            // Find the spinner and click on it.
            onView(withId(R.id.tea_size_spinner)).perform(click());

            // Find the spinner item and click on it.
            onData(is(myArray[i])).perform(click());

            // Find the text view and check that the spinner item
            // is part of the string.
            onView(withId(R.id.summary_tea_size))
                    .check(matches(withText(containsString(myArray[i]))));
        }
    }
}