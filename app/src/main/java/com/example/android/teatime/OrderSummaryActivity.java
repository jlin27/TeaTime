package com.example.android.teatime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class OrderSummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);

        // Display orderSummary
        Intent intent = getIntent();
        String orderSummary = intent.getStringExtra("orderSummary");
        TextView orderSummaryTextView= (TextView) findViewById(R.id.order_summary);
        orderSummaryTextView.setText(orderSummary);
    }
}
