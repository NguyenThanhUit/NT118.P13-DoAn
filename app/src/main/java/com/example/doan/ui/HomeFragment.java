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
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.doan.MainActivity;
import com.example.doan.MainActivityForSaleEmployee;
import com.example.doan.R;
import com.example.doan.customers.AddNewCustomerActivity;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private TextView textViewName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.employee_home_fragment, container, false);

        textViewName = view.findViewById(R.id.tvTenNV);
        if (getArguments() != null) {
            String userName = getArguments().getString("USER_NAME");
            if (userName != null) {
                textViewName.setText("Welcome, " + userName + "!");
            } else {
                textViewName.setText("Welcome!");
            }


            ArrayList<TasksLookUP> tasksLookUPList = getArguments().getParcelableArrayList("TASKS");


            CardView cardViewcreateCustomer = view.findViewById(R.id.card_view_create_customer);
            cardViewcreateCustomer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getContext(), AddNewCustomerActivity.class);
                    startActivity(i);
                }
            });



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
                if (getActivity() instanceof MainActivity) {
                    transaction.replace(R.id.frameLayout3, taskLookupFragment);

                }
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

