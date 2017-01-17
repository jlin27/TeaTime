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

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;


@RunWith(AndroidJUnit4.class)
public class OrderActivityBasicTest {

    @Rule
    public ActivityTestRule<OrderActivity> mActivityTestRule = new ActivityTestRule<>(OrderActivity.class);

    @Test
    public void orderActivityBasicTest() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.increment_button), withText("+")));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction quantityTextView = onView(
                allOf(withId(R.id.quantity_text_view), withText("1"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.quantityLinearLayout),
                                        1),
                                1),
                        isDisplayed()));
        quantityTextView.check(matches(withText("1")));


        ViewInteraction costTextView = onView(
                allOf(withId(R.id.cost_text_view), withText("$5.00"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_tea_detail),
                                        9),
                                1),
                        isDisplayed()));
        costTextView.check(matches(withText("$5.00")));

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
