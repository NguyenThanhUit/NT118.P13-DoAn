package com.example.doan.customers;

import android.content.Context;
import android.content.Intent;
import android.view.View;

public class CustomerClickHandler {
    Context context;
    public CustomerClickHandler(Context context){
        this.context = context;
    }
    public void onFABClicked(View view){
        Intent i = new Intent(view.getContext(), AddNewCustomerActivity.class);
        context.startActivity(i);
    }
}
