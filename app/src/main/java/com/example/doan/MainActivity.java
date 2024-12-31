package com.example.doan;

import android.os.Bundle;
import android.os.Parcelable;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.example.doan.databinding.ActivityMainForAdminBinding;
import com.example.doan.tasks.Tasks;
import com.example.doan.ui.AdminHomeFragment;
import com.example.doan.ui.AdminSettingFragment;
import com.example.doan.ui.AdminStatisticFragment;
import com.example.doan.ui.ListPersonFragment;
import com.example.doan.ui.SettingsFragment;
import com.example.doan.ui.HomeFragment;
import com.example.doan.ui.TaskLookupFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainForAdminBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainForAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new AdminHomeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home) {
                replaceFragment(new AdminHomeFragment());
                return true;
            }
            if (item.getItemId() == R.id.statistic) {
                replaceFragment(new AdminStatisticFragment());
                return true;
            }
            else if (item.getItemId() == R.id.settings) {
                replaceFragment(new AdminSettingFragment());
                return true;
            }
            return false;
        });
    }

    private void replaceFragment(Fragment fragment) {

        String userName = getIntent().getStringExtra("USER_NAME");
        ArrayList<Parcelable> tasklists = getIntent().getParcelableArrayListExtra("TASKS");
        ArrayList<Parcelable> notificationlists = getIntent().getParcelableArrayListExtra("NOTIFICATIONS");

        Bundle bundle = new Bundle();


        if (userName != null) {
            bundle.putString("USER_NAME", userName);
        }
        if (tasklists != null) {
            bundle.putParcelableArrayList("TASKS", tasklists);
        }
        if (notificationlists != null) {
            bundle.putParcelableArrayList("NOTIFICATION", notificationlists);
        }


        fragment.setArguments(bundle);


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout3, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
