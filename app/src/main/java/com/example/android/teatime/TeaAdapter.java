package com.example.android.teatime;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jessicalin on 1/3/17.
 */

public class TeaAdapter extends ArrayAdapter<Tea> {

    public TeaAdapter(Context context, ArrayList<Tea> teas) {
        super(context, 0, teas);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view
        View gridItemView = convertView;
        if (gridItemView == null) {
            gridItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link Tea} object located at this position in the list
        Tea currentTea = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID tea_text_view.
        TextView teaTextView = (TextView) gridItemView.findViewById(R.id.tea_text_view);
        // Get the Tea name translation from the currentTea object and set this text on
        // the Tea TextView.
        teaTextView.setText(currentTea.getTeaName());

        // Set background color for each gridView item
        gridItemView.setBackgroundColor(currentTea.getTeaColor());

        return gridItemView;

    }

}