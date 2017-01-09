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
        int size = intent.getIntExtra("size",0);
        boolean addMilk = intent.getBooleanExtra("addMilk",false);
        boolean addSugar = intent.getBooleanExtra("addSugar",false);
        int quantity = intent.getIntExtra("quantity",0);

        displayOrderSummary(teaName, quantity, price, addMilk, addSugar);
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
    private void displayOrderSummary(String teaName, int quantity, int price, boolean addMilk,
                                      boolean addSugar) {

        String orderSummary = "Tea ordered: " + teaName;
        orderSummary += "\nQuantity: " + quantity;
        if(addMilk) {orderSummary += "\nWith Milk ";}
        if(addSugar) {orderSummary += "\nWith Sugar ";}
        orderSummary += "\nTotal Price: $" + price;

        // Set order summary text
        TextView orderSummaryTextView = (TextView) findViewById(
                R.id.order_summary);
        orderSummaryTextView.setText(orderSummary);
    }
}
