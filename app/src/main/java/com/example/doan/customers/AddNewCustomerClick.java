package com.example.doan.customers;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doan.R;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class AddNewCustomerClick {
    Customers customer;
    Context context;
    CustomerViewModel myViewModel;
    Spinner spinnerCustomerType;
    ImageView imageView;

    public AddNewCustomerClick(Customers customer, Context context, CustomerViewModel myViewModel) {
        this.customer = customer;
        this.context = context;
        this.myViewModel = myViewModel;
    }

    public void onSubmitBtnClicked(View view) {

        if (TextUtils.isEmpty(customer.getName()) || TextUtils.isEmpty(customer.getPhone()) ||
                TextUtils.isEmpty(customer.getEmail()) || TextUtils.isEmpty(customer.getAddress()) ||
                TextUtils.isEmpty(customer.getCategory())) {
            Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin khách hàng", Toast.LENGTH_SHORT).show();
            return;
        }



        Customers newCustomer = new Customers(
                customer.getCid(),
                customer.getName(),
                customer.getPhone(),
                customer.getEmail(),
                customer.getAddress(),
                customer.getCategory(),
                getCurrentDateTime(),
                getCurrentDateTime()
        );


        myViewModel.addnewCustomer(newCustomer);
        Toast.makeText(context, "Thêm khách hàng thành công", Toast.LENGTH_SHORT).show();

    }

    public void onBackBtnClicked(View view) {
        if (context instanceof AppCompatActivity) {
            ((AppCompatActivity) context).finish();
        } else {
            Toast.makeText(context, "Không thể quay lại", Toast.LENGTH_SHORT).show();
        }
    }

    private String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(System.currentTimeMillis());
    }


}
