package com.example.android.teatime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class OrderSummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);

        Intent intent = getIntent();
        String teaName = intent.getStringExtra("teaName");
        int price = intent.getIntExtra("totalPrice",0);
        String size = intent.getStringExtra("size");
        boolean addMilk = intent.getBooleanExtra("addMilk",false);
        boolean addSugar = intent.getBooleanExtra("addSugar",false);
        int quantity = intent.getIntExtra("quantity",0);



        displayOrderSummary(teaName, price, size, addMilk, addSugar, quantity);
    }

    /**
     * Create summary of the order.
     *
     * @param teaName          type of tea
     * @param quantity         quantity ordered
     * @param price            price of the order
     * @param addMilk          is whether or not to add milk to the tea
     * @param addSugar         is whether or not to add sugar to the tea
     * @return text summary
     */
    private void displayOrderSummary(String teaName, int price, String size, boolean addMilk,
                                      boolean addSugar, int quantity) {

        // Set tea name in order summary
        TextView teaNameTextView = (TextView) findViewById(
                R.id.summary_tea_name);
        teaNameTextView.setText(teaName);

        // Set tea size in order summary
        TextView sizeTextView = (TextView) findViewById(
                R.id.summary_tea_size);
        sizeTextView.setText(size);

        // Set milk addition in order summary
        if(addMilk) {
            TextView milkTextView = (TextView) findViewById(
                    R.id.summary_add_milk);
            milkTextView.setText("@string/add_milk");
        }

        // Set sugar addition in order summary
        if(addSugar){
            TextView sugarTextView = (TextView) findViewById(
                    R.id.summary_add_sugar);
            sugarTextView.setText("@string/add_sugar");
        }



    }
}
