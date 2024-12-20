package com.example.doan;

import android.os.Bundle;
import android.os.Parcelable;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.doan.databinding.ActivityMainBinding;
import com.example.doan.databinding.ActivityMainForSaleEmployeeBinding;
import com.example.doan.order.OrderFragment;
import com.example.doan.ui.HomeFragment;
import com.example.doan.ui.ListPersonFragment;
import com.example.doan.ui.SettingsFragment;

import java.util.ArrayList;

public class MainActivityForSaleEmployee extends AppCompatActivity
{
    ActivityMainForSaleEmployeeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainForSaleEmployeeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        binding.bottomNavigationViewForEmployee.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home) {
                replaceFragment(new HomeFragment());
                return true;
            } else if (item.getItemId() == R.id.list_person) {
                replaceFragment(new ListPersonFragment());
                return true;
            } else if (item.getItemId() == R.id.settings) {
                replaceFragment(new SettingsFragment());
                return true;
            } else if(item.getItemId() == R.id.order){
                replaceFragment(new OrderFragment());
            }
            return false;
        });
    }

    private void replaceFragment(Fragment fragment) {

        String userName = getIntent().getStringExtra("USER_NAME");
        ArrayList<Parcelable> tasklists = getIntent().getParcelableArrayListExtra("TASKS");


        Bundle bundle = new Bundle();


        if (userName != null) {
            bundle.putString("USER_NAME", userName);
        }
        if (tasklists != null) {
            bundle.putParcelableArrayList("TASKS", tasklists);
        }


        fragment.setArguments(bundle);


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout4, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}