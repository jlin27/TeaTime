package com.example.android.teatime;

import android.content.Intent;
import android.icu.text.NumberFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity {

    int quantity = 0;
    int totalPrice = 0;
    int basePrice = 5;
    boolean addMilk = false;
    boolean addSugar = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        // Set header name depending on which item was clicked in the gridView
        Intent intent = getIntent();
        String teaName = intent.getStringExtra("teaName");
        TextView teaNameTextView = (TextView) findViewById(R.id.tea_name);
        teaNameTextView.setText(teaName);

        // Set cost default to $0.00
        TextView costTextView = (TextView) findViewById(
                R.id.cost);
        costTextView.setText("$0.00");


        Spinner spinner = (Spinner) findViewById(R.id.tea_size_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.tea_size_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

    }



    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {

        quantity = quantity + 1;
        displayQuantity(quantity);
        totalPrice = calculatePrice();
        displayCost(totalPrice);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {

        quantity = quantity - 1;
        displayQuantity(quantity);
        totalPrice = calculatePrice();
        displayCost(totalPrice);
    }

    /**
     * Recalculate cost depending on whether or not milk checkbox is clicked.
     */
    public void checkMilk(View view) {

        Toast.makeText(OrderActivity.this,""+ totalPrice + " " + quantity,Toast.LENGTH_SHORT).show();
        totalPrice = calculatePrice();
        displayCost(totalPrice);
    }

    /**
     * Recalculate cost depending on whether or not sugar checkbox is clicked.
     */
    public void checkSugar(View view) {

        Toast.makeText(OrderActivity.this,""+ totalPrice + " " + quantity,Toast.LENGTH_SHORT).show();
        totalPrice = calculatePrice();
        displayCost(totalPrice);
    }

    /**
     * Calculates the totalPrice of the order.
     *
     * @return total totalPrice
     */
    private int calculatePrice() {

        // Figure out if the user wants milk
        CheckBox milkCheckBox = (CheckBox) findViewById(R.id.milkCheckBox);
        addMilk = milkCheckBox.isChecked();

        // Figure out if the user wants sugar
        CheckBox sugarCheckBox = (CheckBox) findViewById(R.id.sugarCheckBox);
        addSugar = sugarCheckBox.isChecked();

        totalPrice += quantity * basePrice;

        // If the user wants milk, add $1 per cup
        if (addMilk) {
            totalPrice += quantity * 1;
        }

        // If the user wants sugar, add $1 per cup
        if (addSugar) {
            totalPrice += quantity *  1;
        }

        // Calculate the total order totalPrice by multiplying by the quantity
        return totalPrice;
    }

    /**
     * This method displays the given quantity value on the screen then
     * calculates and displays the cost
     */
    private void displayQuantity(int numberOfTeas) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfTeas);

    }

    private void displayCost(int totalPrice){
        TextView costTextView = (TextView) findViewById(
                R.id.cost);

        String convertPrice = NumberFormat.getCurrencyInstance().format(totalPrice);

        costTextView.setText("" + convertPrice);

    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {


        // Display the order summary on the screen
        String orderSummary = createOrderSummary(totalPrice, addMilk, addSugar);

        // Create a new intent to open the {@link OrderSummaryActvitiy}
        Intent intent = new Intent(OrderActivity.this, OrderSummaryActivity.class);
        intent.putExtra("orderSummary",orderSummary);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * Create summary of the order.
     *
     * @param price           of the order
     * @param addMilk         is whether or not to add milk to the tea
     * @param addSugar        is whether or not to add sugar to the tea
     * @return text summary
     */
    private String createOrderSummary(int price, boolean addMilk, boolean addSugar) {
        String orderSummary = "The total cost is $" + price;
        orderSummary += "\n" + "Your " + quantity + "teas will be ready soon!";
        return orderSummary;
    }
}
