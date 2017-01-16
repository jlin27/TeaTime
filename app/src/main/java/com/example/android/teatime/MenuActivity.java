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

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.android.teatime.IdlingResource.SimpleIdlingResource;
import com.example.android.teatime.model.Tea;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements ImageDelayer.DelayerCallback {


    Intent teaIntent;

    // The Idling Resource which will be null in production.
    @Nullable private SimpleIdlingResource mIdlingResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        // Create an arraylist of teas
        final ArrayList<Tea> teas = new ArrayList<>();
        teas.add(new Tea(getString(R.string.black_tea_name), R.drawable.black_tea));
        teas.add(new Tea(getString(R.string.green_tea_name), R.drawable.green_tea));
        teas.add(new Tea(getString(R.string.white_tea_name), R.drawable.white_tea));
        teas.add(new Tea(getString(R.string.oolong_tea_name), R.drawable.oolong_tea));
        teas.add(new Tea(getString(R.string.puerh_tea_name), R.drawable.puerh_tea));
        teas.add(new Tea(getString(R.string.matcha_tea_name), R.drawable.sample_5));

        // Create a {@link TeaAdapter}, whose data source is a list of {@link Tea}s.
        // The adapter know how to create grid items for each item in the list.
        GridView gridview = (GridView) findViewById(R.id.gridView);
        TeaAdapter adapter = new TeaAdapter(this, R.layout.grid_item_layout, teas);
        gridview.setAdapter(adapter);

        Log.v("MenuActivity", "adapter has been set");


        // Set a click listener on that View
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Tea item = (Tea) adapterView.getItemAtPosition(position);
                // Set the intent to open the {@link TeaDetailActivity}
                teaIntent = new Intent(MenuActivity.this, OrderActivity.class);

                // The delayer notifies the activity via a callback
                // Pass in the tea name to be displayed in the detail activity
                String teaName = item.getTeaName();
                int teaImage = item.getImageResourceId();

                teaIntent.putExtra("teaName", teaName); //TODO: Make keys into constants
                teaIntent.putExtra("teaImage", teaImage);

                if (teaIntent.resolveActivity(getPackageManager()) != null) {
                    // Set a temporary delay toast message
                    Context context = getApplicationContext();
                    String text = getString(R.string.loading_msg);
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();


                    // Submit the image to the delayer
                    ImageDelayer.processImage(teaImage, MenuActivity.this, mIdlingResource);
                }
            }
        });

    }


    @Override
    public void onDone(int image) {

        // The delayer starts the activity via a callback.
        startActivity(teaIntent);
    }

    /**
     * Only called from test, creates and returns a new {@link SimpleIdlingResource}.
     */
    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new SimpleIdlingResource();
        }
        return mIdlingResource;
    }
}
