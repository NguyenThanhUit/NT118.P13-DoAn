package com.example.room_db;

import android.app.Activity;
import android.app.ActivityManager;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.room_db.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Data source
    private ContactDatabase contactDatabase;
    private ArrayList<Contacts> contacts = new ArrayList<>();
    //Adapter
    private MyAdapter myAdapter;
    //Binding
    private ActivityMainBinding mainBinding;
    private MainActivityclickHandle handlers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        handlers = new MainActivityclickHandle(this);
        mainBinding.setClickHandle(handlers);
        RecyclerView recyclerView = mainBinding.recycleview;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        contactDatabase = ContactDatabase.getInstance(this);
        MyViewModel myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

        Contacts c1 = new Contacts( "Jack", "jack@gmail.com");
        myViewModel.addnewContact(c1);

        //Loading data from ROOM DB
        myViewModel.getAllContacts().observe(this, new Observer<List<Contacts>>() {
            @Override
            public void onChanged(List<Contacts> contacts) {
                for (Contacts c : contacts){
                    Log.v("Tagy", c.getName());
                    contacts.add(c);
                }
                myAdapter.notifyDataSetChanged();
            }
        });
        myAdapter = new MyAdapter(contacts);
        recyclerView.setAdapter(myAdapter);
    }
}