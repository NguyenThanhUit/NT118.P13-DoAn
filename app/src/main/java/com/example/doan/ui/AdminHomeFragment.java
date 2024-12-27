package com.example.doan.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.doan.R;
import com.example.doan.tasks.AssignTaskActivity;

public class AdminHomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin_home_fragment, container, false);
        CardView cardViewCustomer = view.findViewById(R.id.card_view_customer);
        cardViewCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListPersonFragment fragment = new ListPersonFragment();
                getParentFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayout3, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });


        CardView cardViewTask = view.findViewById(R.id.card_view_task);
        cardViewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), AssignTaskActivity.class);
                startActivity(i);
            }
        });

        return view;
    }
}