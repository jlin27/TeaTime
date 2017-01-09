package com.example.android.teatime;

import android.content.Intent;
import android.icu.text.NumberFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    int quantity = 0;
    int totalPrice = 0;
    int basePrice = 5; // Small tea price with no additions
    int medAddPrice = 1; // Additional price over basePrice for medium tea price with no additions
    int largeAddPrice = 2; // Additional price over basePrice for large tea price with no additions
    boolean addMilk = false;
    boolean addSugar = false;
    String teaName ="";
    int size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        // Set header name depending on which item was clicked in the gridView
        Intent intent = getIntent();
        teaName = intent.getStringExtra("teaName");
        TextView teaNameTextView = (TextView) findViewById(R.id.tea_name);
        teaNameTextView.setText(teaName);

        // Set cost default to $0.00
        TextView costTextView = (TextView) findViewById(
                R.id.cost);
        costTextView.setText("$0.00");

        setupSpinner();

    }

    /**
     * Setup the dropdown spinner for user to select tea size
     */
    private void setupSpinner() {


        Spinner mSizeSpinner = (Spinner) findViewById(R.id.tea_size_spinner);

        // Create an ArrayAdapter using the string array and a default mSizeSpinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.tea_size_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the mSizeSpinner
        mSizeSpinner.setAdapter(adapter);

        // Set the integer mSelected to the constant values
        mSizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.size_small))) {
                        size = 0; // Small
                    } else if (selection.equals(getString(R.string.size_medium))) {
                        size = 1; // Medium
                    } else {
                        size = 2; // Large
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
                /**
                 * Need to add code that prevents user from moving forward if not selected
                 */
            }
        });

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

        totalPrice = calculatePrice();
        displayCost(totalPrice);
    }

    /**
     * Recalculate cost depending on whether or not sugar checkbox is clicked.
     */
    public void checkSugar(View view) {

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


        // Determine tea size price
        if (size == 0) {
            totalPrice = quantity * basePrice;
        } else if (size == 1) {
            totalPrice = quantity * (basePrice + medAddPrice);
        } else {
            totalPrice = quantity * (basePrice + largeAddPrice);
        }

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

        // Create a new intent to open the {@link OrderSummaryActvitiy}
        Intent intent = new Intent(OrderActivity.this, OrderSummaryActivity.class);
        intent.putExtra("totalPrice",totalPrice);
        intent.putExtra("teaName",teaName);
        intent.putExtra("size",size);
        intent.putExtra("addMilk",addMilk);
        intent.putExtra("addSugar",addSugar);
        intent.putExtra("quantity",quantity);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
