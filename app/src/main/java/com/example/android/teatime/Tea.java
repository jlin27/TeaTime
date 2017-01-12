package com.example.android.teatime;

import android.graphics.Bitmap;

/**
 * Created by jessicalin on 1/3/17.
 */

public class Tea {

    private String mTeaName;
    private int mImageResourceId;
    private Bitmap image;

    public Tea(String teaName, int imageResourceId) {
        mTeaName = teaName;
        mImageResourceId = imageResourceId;
    }

    public String getTeaName() {

        return mTeaName;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }

}

