package com.example.android.teatime;

import android.content.Intent;
import android.icu.text.NumberFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    int quantity = 0;
    int totalPrice = 0;
    int smallPrice = 5;
    int medPrice = 6;
    int largePrice = 7;
    String milkType;
    String sugarType;
    String teaName ="";
    int teaImage;
    String size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        // Set header name and image depending on which item was clicked in the gridView
        Intent intent = getIntent();
        teaName = intent.getStringExtra("teaName");
        teaImage = intent.getIntExtra("teaImage",0);

        TextView teaNameTextView = (TextView) findViewById(R.id.tea_name);
        teaNameTextView.setText(teaName);


        ImageView teaImageView = (ImageView) findViewById(R.id.tea_image);
        teaImageView.setImageResource(teaImage);


        // Set cost default to $0.00
        TextView costTextView = (TextView) findViewById(
                R.id.cost);
        costTextView.setText("$0.00");

        setupSizeSpinner();
        setupMilkSpinner();
        setupSugarSpinner();

    }

    /**
     * Setup the dropdown spinner for user to select tea size
     */
    private void setupSizeSpinner() {


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
                        size = "Small";
                    } else if (selection.equals(getString(R.string.size_medium))) {
                        size = "Medium";
                    } else {
                        size = "Large";
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
     * Setup the dropdown spinner for user to select milk type
     */
    private void setupMilkSpinner() {


        Spinner mSizeSpinner = (Spinner) findViewById(R.id.milk_spinner);

        // Create an ArrayAdapter using the string array and a default mSizeSpinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.milk_array, android.R.layout.simple_spinner_item);

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
                    if (selection.equals("None")) {
                        milkType = "None";
                    } else if (selection.equals("Nonfat Milk")) {
                        milkType = "Nonfat Milk";
                    } else if (selection.equals("1% Milk")) {
                        milkType = "1% Milk";
                    } else if (selection.equals("2% Milk")) {
                        milkType = "2% Milk";
                    } else {
                        milkType = "Whole Milk";

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
     * Setup the dropdown spinner for user to select amount of sugar
     */
    private void setupSugarSpinner() {


        Spinner mSizeSpinner = (Spinner) findViewById(R.id.sugar_spinner);

        // Create an ArrayAdapter using the string array and a default mSizeSpinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.sugar_array, android.R.layout.simple_spinner_item);

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
                    if (selection.equals("0% - Not Sweet")) {
                        sugarType = "0% - Not Sweet";
                    } else if (selection.equals("25% - Slightly Sweet")) {
                        milkType = "25% - Slightly Sweet";
                    } else if (selection.equals("50% - Half Sweet")) {
                        milkType = "50% - Half Swee";
                    } else if (selection.equals("75% - Moderately Sweet")) {
                        milkType = "75% - Moderately Sweet";
                    } else {
                        milkType = "100% - Full Sweetness";

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
     * Calculates the totalPrice of the order.
     *
     * @return total totalPrice
     */
    private int calculatePrice() {

        // Determine tea size price
        if (size.equals("Small")) {
            totalPrice = quantity * smallPrice;
        } else if (size.equals("Medium")) {
            totalPrice = quantity * medPrice;
        } else {
            totalPrice = quantity * largePrice;
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

    @RequiresApi(api = Build.VERSION_CODES.N)
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
        intent.putExtra("milkType",milkType);
        intent.putExtra("sugarType",sugarType);
        intent.putExtra("quantity",quantity);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
