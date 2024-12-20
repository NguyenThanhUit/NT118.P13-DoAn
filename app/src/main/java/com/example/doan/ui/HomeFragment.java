package com.example.doan.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.doan.MainActivity;
import com.example.doan.MainActivityForSaleEmployee;
import com.example.doan.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private TextView textViewName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate layout cho Fragment
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        textViewName = view.findViewById(R.id.tvTenNV);


        if (getArguments() != null) {
            String userName = getArguments().getString("USER_NAME");
            if (userName != null) {
                textViewName.setText("Welcome, " + userName + "!");
            } else {
                textViewName.setText("Welcome!");
            }


            ArrayList<TasksLookUP> tasksLookUPList = getArguments().getParcelableArrayList("TASKS");


            TaskLookupFragment fragment = new TaskLookupFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("TASKS", tasksLookUPList);
            fragment.setArguments(bundle);

        }

        LinearLayout linearLayoutTask = view.findViewById(R.id.lnTask);
        linearLayoutTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                TaskLookupFragment taskLookupFragment = new TaskLookupFragment();

                taskLookupFragment.setArguments(getArguments());
                //Neu la activity cua nhan vien cham soc khach hang
                if (getActivity() instanceof MainActivity) {
                    transaction.replace(R.id.frameLayout3, taskLookupFragment);

                }//Neu la activity cua nhan vien ban hang
                else if (getActivity() instanceof MainActivityForSaleEmployee) {

                    transaction.replace(R.id.frameLayout4, taskLookupFragment);
                }
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

        return view;
    }
}

