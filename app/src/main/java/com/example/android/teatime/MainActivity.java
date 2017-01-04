package com.example.android.teatime;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create an arraylist of teas
        ArrayList<Tea> teas = new ArrayList<Tea>();
        teas.add(new Tea("Black Tea", Color.parseColor("#9E9E9E")));
        teas.add(new Tea("Green Tea", Color.parseColor("#84C542")));
        teas.add(new Tea("White Tea", Color.parseColor("#FFFFFF")));
        teas.add(new Tea("Oolong Tea", Color.parseColor("#FF80AB")));
        teas.add(new Tea("Pu-erh Tea", Color.parseColor("#FFD600")));
        teas.add(new Tea("Matcha Tea", Color.parseColor("#1B5E20")));

        // Create a {@link TeaAdapter}, whose data source is a list of {@link Tea}s.
        // The adapter know how to create list items for each item in the list.
        TeaAdapter adapter = new TeaAdapter(this,teas);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(adapter);


        // Set a click listener on that View
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "" + position,
                        Toast.LENGTH_SHORT).show();
                // Create a new intent to open the {@link TeaDetailActivity}
                Intent teaIntent = new Intent(MainActivity.this, TeaDetailActivity.class);
                // Start the new activity
                startActivity(teaIntent);
            }
        });

    }
}
