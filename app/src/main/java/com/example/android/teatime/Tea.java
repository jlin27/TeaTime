package com.example.android.teatime;

/**
 * Created by jessicalin on 1/3/17.
 */

public class Tea {

    private String mTeaName;
    private int mTeaColor;

    public Tea(String teaName, int teaColor) {
        mTeaName = teaName;
        mTeaColor = teaColor;
    }

    public String getTeaName() {
        return mTeaName;
    }

    public int getTeaColor() {
        return mTeaColor;
    }
}
