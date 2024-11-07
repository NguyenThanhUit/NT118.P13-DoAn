package com.example.doan.domain.contact;

import android.content.Context;
import android.content.Intent;
import android.view.View;

public class MainActivityClickHandle {
    Context context;

    public MainActivityClickHandle(Context context) {
        this.context = context;
    }
    public void onFABCClicked(View view){
        Intent i = new Intent(view.getContext(), AddNewContactActivity.class);
        context.startActivity(i);
    }
}