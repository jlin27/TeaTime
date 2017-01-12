package com.example.android.teatime;

import android.content.Intent;
import android.icu.text.NumberFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
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
        String milkType = intent.getStringExtra("milkType");
        String sugarType = intent.getStringExtra("sugarType");
        int quantity = intent.getIntExtra("quantity",0);



        displayOrderSummary(teaName, price, size, milkType, sugarType, quantity);
    }

    /**
     * Create summary of the order.
     *
     * @param teaName          type of tea
     * @param quantity         quantity ordered
     * @param price            price of the order
     * @param milkType         type of milk to add
     * @param sugarType        amount of sugar to add
     * @return text summary
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void displayOrderSummary(String teaName, int price, String size, String milkType,
                                     String sugarType, int quantity) {

        // Set tea name in order summary
        TextView teaNameTextView = (TextView) findViewById(
                R.id.summary_tea_name);
        teaNameTextView.setText(teaName);

        // Set quantity in order summary
        TextView quantityTextView = (TextView) findViewById(
                R.id.summary_quantity);
        quantityTextView.setText(String.valueOf(quantity));

        // Set tea size in order summary
        TextView sizeTextView = (TextView) findViewById(
                R.id.summary_tea_size);
        sizeTextView.setText(size);

        // Set milk type in order summary
        TextView milkTextView = (TextView) findViewById(
                    R.id.summary_milk_type);
            milkTextView.setText(milkType);

        // Set sugar amount in order summary
            TextView sugarTextView = (TextView) findViewById(
                    R.id.summary_sugar_amount);
            sugarTextView.setText(sugarType);

        // Set total price in order summary
        TextView priceTextView = (TextView) findViewById(
                R.id.summary_total_price);

        String convertPrice = NumberFormat.getCurrencyInstance().format(price);
        priceTextView.setText(convertPrice);



    }
}
