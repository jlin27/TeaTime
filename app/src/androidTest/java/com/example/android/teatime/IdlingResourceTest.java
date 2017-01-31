/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.teatime;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import static org.hamcrest.Matchers.containsString;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Usually Espresso syncs all view operations with the UI thread as well as AsyncTasks, but it can't
 * do so with custom resources (e.g. activity or service). For such cases, we can register the
 * custom resource and Espresso will wait for the resource to be idle before
 * executing a view operation.
 *
 * In this example, we simulate an idling situation. This test is the same as the
 * MenuActivityScreenTest but with an Idling Resource to help with synchronization.
 *
 * We added an idling period from when the user clicks on a GridView item
 * in MenuActivity to when corresponding order activity appears. This is to simulate potential
 * delay that could happen if this data were being retrieved from the web. Without registering the
 * custom resources, this test would fail because the test would proceed without waiting
 * for the Idling Resource.
 */


@RunWith(AndroidJUnit4.class)
public class IdlingResourceTest {

    /**
     * The ActivityTestRule is a rule provided by Android used for functional testing of a single
     * activity. The activity that will be tested, OrderActivity in this case, will be launched
     * before each test that's annotated with @Test and before methods annotated with @Before.
     *
     * The activity will be terminated after the test and methods annotated with @After are
     * complete. This rule allows you to directly access the activity during the test.
     */
    @Rule
    public ActivityTestRule<OrderActivity> mActivityTestRule =
            new ActivityTestRule<>(OrderActivity.class);

    private IdlingResource mIdlingResource;
    private static final String ORDER_ACTIVITY_TEA_IMAGE = "brewing tea";

    // Registers any resource that needs to be synchronized with Espresso before the test is run.
    public void registerIdlingResource() {
        mIdlingResource = mActivityTestRule.getActivity().getIdlingResource();
        // To prove that the test fails, omit this call:
        Espresso.registerIdlingResources(mIdlingResource);
    }

    @Test
    public void idlingResourceTest() {
        // Click on the "Brew Tea" button and verify a tea image appears.
        onView(withId(R.id.brew_tea_button)).perform(click());

        // Verify that the correct tea image appears in the OrderActivity
        onView(withId(R.id.order_activity_tea_image))
                .check(matches(withContentDescription(containsString(ORDER_ACTIVITY_TEA_IMAGE))));


    }

    // Remember to unregister resources when not needed to avoid malfunction.
    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            Espresso.unregisterIdlingResources(mIdlingResource);
        }

    }

}
