package com.example.doan;

import android.os.Bundle;
<<<<<<< HEAD
import android.os.Parcelable;

=======
>>>>>>> 0a54650359fd6f2b2d0508867501cb6089f648b7

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

<<<<<<< HEAD

import com.example.doan.databinding.ActivityMainForAdminBinding;
import com.example.doan.tasks.Tasks;
import com.example.doan.ui.AdminHomeFragment;
import com.example.doan.ui.AdminSettingFragment;
import com.example.doan.ui.GoodsFragment;
import com.example.doan.ui.ListPersonFragment;
import com.example.doan.ui.SettingsFragment;
=======
import com.example.doan.databinding.ActivityMainBinding;
import com.example.doan.ui.CustomerFragment;
>>>>>>> 0a54650359fd6f2b2d0508867501cb6089f648b7
import com.example.doan.ui.HomeFragment;
import com.example.doan.ui.TaskLookupFragment;

public class MainActivity extends AppCompatActivity {

<<<<<<< HEAD
    ActivityMainForAdminBinding binding;

=======
>>>>>>> 0a54650359fd6f2b2d0508867501cb6089f648b7
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
<<<<<<< HEAD
        binding = ActivityMainForAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new AdminHomeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home) {
                replaceFragment(new AdminHomeFragment());
                return true;
            }else if(item.getItemId() == R.id.order){
                replaceFragment(new GoodsFragment());
                return true;
            } else if (item.getItemId() == R.id.settings) {
                replaceFragment(new AdminSettingFragment());
                return true;
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
        fragmentTransaction.replace(R.id.frameLayout3, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
=======

>>>>>>> 0a54650359fd6f2b2d0508867501cb6089f648b7
    }
}
