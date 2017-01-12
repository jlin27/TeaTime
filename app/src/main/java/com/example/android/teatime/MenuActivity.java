package com.example.android.teatime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Create an arraylist of teas
        final ArrayList<Tea> teas = new ArrayList<>();
        teas.add(new Tea("Black Tea", R.drawable.black_tea));
        teas.add(new Tea("Green Tea", R.drawable.green_tea));
        teas.add(new Tea("White Tea", R.drawable.white_tea));
        teas.add(new Tea("Oolong Tea", R.drawable.oolong_tea));
        teas.add(new Tea("Pu-erh Tea", R.drawable.puerh_tea));
        teas.add(new Tea("Matcha Tea", R.drawable.sample_5));

        // Create a {@link TeaAdapter}, whose data source is a list of {@link Tea}s.
        // The adapter know how to create grid items for each item in the list.
        GridView gridview = (GridView) findViewById(R.id.gridView);
        TeaAdapter adapter = new TeaAdapter(this,R.layout.grid_item_layout,teas);
        gridview.setAdapter(adapter);


        // Set a click listener on that View
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

               Tea item = (Tea) adapterView.getItemAtPosition(position);
                // Create a new intent to open the {@link TeaDetailActivity}
                Intent teaIntent = new Intent(MenuActivity.this, OrderActivity.class);
                // Pass in the tea name to be displayed in the detail activity
                String teaName = item.getTeaName().toString();
                int teaImage = item.getImageResourceId();

                teaIntent.putExtra("teaName",teaName);
                teaIntent.putExtra("teaImage",teaImage);
                // Start the new activity
                if (teaIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(teaIntent);
                }
            }
        });

    }
}
