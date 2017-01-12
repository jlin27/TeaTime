/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

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


    private int mQuantity = 0;
    private int mTotalPrice = 0;

    // TODO should these be constants? Do they change?
    private int mSmallPrice = 5;
    private int mMedPrice = 6;
    private int mLargePrice = 7;
    private String mMilkType;
    private String mSugarType;
    private String mTeaName = "";
    private int mTeaImage;
    private String mSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        // Set header name and image depending on which item was clicked in the gridView
        Intent intent = getIntent();
        mTeaName = intent.getStringExtra("teaName"); // TODO these keys should be constants
        mTeaImage = intent.getIntExtra("teaImage", 0);

        TextView teaNameTextView = (TextView) findViewById(R.id.tea_name_text_view);
        teaNameTextView.setText(mTeaName);


        ImageView teaImageView = (ImageView) findViewById(R.id.tea_image_view);
        teaImageView.setImageResource(mTeaImage);


        // Set cost default to $0.00
        TextView costTextView = (TextView) findViewById(
                R.id.cost_text_view);
        costTextView.setText("$0.00");

        setupSizeSpinner();
        setupMilkSpinner();
        setupSugarSpinner();

    }

    /**
     * Sets up the dropdown spinner for user to select tea mSize
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
                    if (selection.equals(getString(
                            R.string.size_small))) { //TODO this can be a switch, also the
                        // strings should be constants
                        mSize = "Small";
                    } else if (selection.equals(getString(R.string.size_medium))) {
                        mSize = "Medium";
                    } else {
                        mSize = "Large";
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
     * Sets up the dropdown spinner for user to select milk type
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
                    if (selection.equals(
                            "None")) { // TODO should all be strings, and probably in a
                        // case/switch...also why not just set milktype to the selection?
                        mMilkType = "None";
                    } else if (selection.equals("Nonfat Milk")) {
                        mMilkType = "Nonfat Milk";
                    } else if (selection.equals("1% Milk")) {
                        mMilkType = "1% Milk";
                    } else if (selection.equals("2% Milk")) {
                        mMilkType = "2% Milk";
                    } else {
                        mMilkType = "Whole Milk";

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
                if (!TextUtils.isEmpty(selection)) { // TODO same questions as above
                    if (selection.equals("0% - Not Sweet")) {
                        mSugarType = "0% - Not Sweet";
                    } else if (selection.equals("25% - Slightly Sweet")) {
                        mMilkType = "25% - Slightly Sweet";
                    } else if (selection.equals("50% - Half Sweet")) {
                        mMilkType = "50% - Half Swee";
                    } else if (selection.equals("75% - Moderately Sweet")) {
                        mMilkType = "75% - Moderately Sweet";
                    } else {
                        mMilkType = "100% - Full Sweetness";

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
     * Increments the quantity and recalculates the price
     */
    public void increment(View view) {

        mQuantity = mQuantity + 1;
        displayQuantity(mQuantity);
        mTotalPrice = calculatePrice();
        displayCost(mTotalPrice);
    }

    /**
     * Decrements the quantity and recalculates the price
     */
    public void decrement(View view) {

        mQuantity = mQuantity - 1;
        displayQuantity(mQuantity);
        mTotalPrice = calculatePrice();
        displayCost(mTotalPrice);
    }


    /**
     * Calculates the TotalPrice of the order.
     *
     * @return total mTotalPrice
     */
    private int calculatePrice() {

        // Determine tea size price
        if (mSize.equals("Small")) {
            mTotalPrice = mQuantity * mSmallPrice;
        } else if (mSize.equals("Medium")) {
            mTotalPrice = mQuantity * mMedPrice;
        } else {
            mTotalPrice = mQuantity * mLargePrice;
        }

        // Calculate the total order mTotalPrice by multiplying by the mQuantity
        return mTotalPrice; // TODO so if this is a private method, then it will only be used in
        // this class, where mTotalPrice is an instance variable and in scope, so why return?
    }

    /**
     * Displays the given mQuantity value on the screen then
     * calculates and displays the cost
     */
    private void displayQuantity(int numberOfTeas) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(String.valueOf(numberOfTeas));

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void displayCost(int totalPrice) {
        TextView costTextView = (TextView) findViewById(
                R.id.cost_text_view);

        String convertPrice = NumberFormat.getCurrencyInstance().format(totalPrice);

        costTextView.setText(String.valueOf(convertPrice));

    }

    /**
     * This method is called when the order button is clicked. //TODO fix comment phrasing
     */
    public void submitOrder(View view) {

        // Create a new intent to open the {@link OrderSummaryActvitiy}
        Intent intent = new Intent(OrderActivity.this, OrderSummaryActivity.class);
        intent.putExtra("totalPrice", mTotalPrice);
        intent.putExtra("teaName", mTeaName);
        intent.putExtra("size", mSize);
        intent.putExtra("milkType", mMilkType);
        intent.putExtra("sugarType", mSugarType);
        intent.putExtra("quantity", mQuantity);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
