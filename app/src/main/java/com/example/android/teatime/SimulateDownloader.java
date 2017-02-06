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

import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.test.espresso.IdlingResource;
import com.example.android.teatime.IdlingResource.SimpleIdlingResource;

/**
 * Simulates a delay running on a different thread. After which, a message is
 * returned via a callback when the delay time it up.
 * <p>
 * A long-running operation on a different thread such as this results in problems with
 * Espresso if an {@link IdlingResource} is not implemented and registered.
 */
class SimulateDownloader {

    private static final int DELAY_MILLIS = 3000;
    private static final String doneMsg = "Your tea is ready!";

    interface DelayerCallback{
        void onDone(String doneMsg);
    }

    /**
     * This method is meant to simulate running background operation (such as communicating with web
     * services). This could be similar to downloading an image from the internet.
     * For simplicity, in this hypothetical situation, we've added a delay after the user clicks the
     * {@link layout/activity_order.xml/brew_tea_button}
     * We simulate a delay time of {@link #DELAY_MILLIS} and once the time
     * is up we return message back to the calling activity via a {@link DelayerCallback}.
     * @param callback used to notify the caller asynchronously
     */

    static void downloadProcess(final DelayerCallback callback,
                              @Nullable final SimpleIdlingResource idlingResource) {
        /**
         * The IdlingResource is null in production as set by the @Nullable annotation which means
         * the value is allowed to be null.
         *
         * If the idle state is true, Espresso can perform the next action.
         * If the idle state is false, Espresso will wait until it is true before
         * performing the next action.
          */
        if (idlingResource != null) {
            idlingResource.setIdleState(false);
        }

        /**
         * {@link postDelayed} allows the {@link Runnable} to be run after the specified amount of
         * time set in DELAY_MILLIS elapses. An object that implements the Runnable interface
         * creates a thread. When this thread starts, the object's run method is called.
         *
         * After the time elapses, if there is a callback we return the image resource ID and
         * set the idle state to true.
         */
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onDone(doneMsg);
                    if (idlingResource != null) {
                        idlingResource.setIdleState(true);
                    }
                }
            }
        }, DELAY_MILLIS);
    }
}
