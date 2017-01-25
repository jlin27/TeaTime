package com.example.android.teatime;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.test.espresso.IdlingResource;
import android.widget.Toast;

import com.example.android.teatime.IdlingResource.SimpleIdlingResource;

/**
 * Takes a String and returns it after a while via a callback.
 * <p>
 * This executes a long-running operation on a different thread that results in problems with
 * Espresso if an {@link IdlingResource} is not implemented and registered.
 */
class ImageDownloader {

    private static final int DELAY_MILLIS = 3000;
    private static final int image = R.drawable.order_activity_tea_image;

    interface DelayerCallback{
        void onDone(int image);
    }

    /**
     * This method is meant to simulate the delay time when downloading an image file from the;
     * however, in this hypothetical situation, we've provided the image.
     * We simulate a delay time of {@link #DELAY_MILLIS} and once the time
     * is up we return the image back to the calling activity via a {@link DelayerCallback}.
     * @param callback used to notify the caller asynchronously
     */

    static void downloadImage(Context context, final DelayerCallback callback,
                              @Nullable final SimpleIdlingResource idlingResource) {

        Context mContext = context.getApplicationContext();
        String text = mContext.getString(R.string.loading_msg);
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(mContext, text, duration);
        toast.show();

        // The IdlingResource is null in production. Espresso waits for the app to be "idle" before
        // performing the next action and checking the assertion.
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
