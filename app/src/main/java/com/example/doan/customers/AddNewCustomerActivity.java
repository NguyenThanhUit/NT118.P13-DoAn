package com.example.doan.customers;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.databinding.DataBindingUtil;
import com.example.doan.R;
import com.example.doan.databinding.CreateCustomerBinding;


public class AddNewCustomerActivity extends AppCompatActivity {
    private CreateCustomerBinding addCustomerBinding;
    private AddNewCustomerClick handlers;
    private Customers customers;
    private CustomerViewModel myViewModel;
    private Spinner mySpinner;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myViewModel = new ViewModelProvider(this).get(CustomerViewModel.class);

        customers = new Customers();

        addCustomerBinding = DataBindingUtil.setContentView(
                this,
                R.layout.create_customer
        );

        mySpinner = findViewById(R.id.spinnerCustomerType);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.customer_type_array,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(adapter);


        imageView = findViewById(R.id.indicatorSquare);

        handlers = new AddNewCustomerClick(customers, this, myViewModel);


        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedCategory = (String) parentView.getItemAtPosition(position);
                customers.setCategory(selectedCategory);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });


        addCustomerBinding.setCustomer(customers);
        addCustomerBinding.setClickHandler(handlers);
    }
}
