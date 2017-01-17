package com.example.android.teatime;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;

/**
 * This test demos a user clicking on a GridView item in MenuActivity which opens up the
 * correspoding OrderActivity.
 *
 * This test does not utilize Idling Resources yet. For that, see the IdlingResourcesTest.
 * for the Idling Resource.
 */


@RunWith(AndroidJUnit4.class)
public class MenuActivityScreenTest {

    @Rule
    public ActivityTestRule<MenuActivity> mActivityTestRule = new ActivityTestRule<>(MenuActivity.class);


    @Test
    public void MenuActivityScreenTest3() {

        onData(anything()).inAdapterView(withId(R.id.gridView)).atPosition(1).perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.tea_name_text_view), withText("Green Tea"),
                        childAtPosition(
                                allOf(withId(R.id.activity_tea_detail_header),
                                        childAtPosition(
                                                withId(R.id.activity_tea_detail),
                                                0)),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Green Tea")));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

}
