package com.example.room_db;

import android.content.Context;
import android.content.Intent;
import android.view.View;

public class MainActivityclickHandle {
    Context context;

    public MainActivityclickHandle(Context context) {
        this.context = context;
    }
    public void onFABCClicked(View view){
        Intent i = new Intent(view.getContext(), AddNewContactActiviy.class);
        context.startActivity(i);
    }
}
