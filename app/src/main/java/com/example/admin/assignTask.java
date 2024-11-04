package com.example.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class assignTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_assign_task);

        ListView lvAssign = findViewById(R.id.lvAssign);
        ArrayList<String> assignList = new ArrayList<>();
        assignList.add("NV01");
        assignList.add("NV02");
        assignList.add("NV03");
        assignList.add("NV04");
        assignList.add("NV05");
        assignList.add("NV06");
        assignList.add("NV07");
        assignList.add("NV08");
        assignList.add("NV09");
        assignList.add("NV10");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                assignList
        );
         lvAssign.setAdapter(adapter);

        ImageButton imgBtnBack = findViewById(R.id.imgBtnBack3);
        imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}