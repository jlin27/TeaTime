package com.example.android.teatime;

import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.test.espresso.IdlingResource;

import com.example.android.teatime.IdlingResource.SimpleIdlingResource;

/**
 * Takes a String and returns it after a while via a callback.
 * <p>
 * This executes a long-running operation on a different thread that results in problems with
 * Espresso if an {@link IdlingResource} is not implemented and registered.
 */
class ImageDelayer {

    private static final int DELAY_MILLIS = 3000;

    interface DelayerCallback{
        void onDone(int image);
    }

    /**
     * Takes a String and returns it after {@link #DELAY_MILLIS} via a {@link DelayerCallback}.
     * @param image the image resource id that will be returned via the callback
     * @param callback used to notify the caller asynchronously
     */

    static void processImage(final int image, final DelayerCallback callback,
                               @Nullable final SimpleIdlingResource idlingResource) {
        // The IdlingResource is null in production.
        if (idlingResource != null) {
            // TODO concisely, what does a resource being "idle" mean
            idlingResource.setIdleState(false);
        }

        // Delay the execution, return message via callback.
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onDone(image);
                    if (idlingResource != null) {
                        // TODO so the test runs when this is true?
                        // TODO why not actually load an image? what would that look like
                        // TODO why not use counting resource? That sounds a lot more straight forward
                        idlingResource.setIdleState(true);
                    }
                }
            }
        }, DELAY_MILLIS);
    }
}
