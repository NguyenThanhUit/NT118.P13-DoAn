package com.example.doan.domain.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.doan.R;

public class AdminHomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.admin_home_fragment, container, false);

        CardView cvEmployeeList = rootView.findViewById(R.id.card_view_employee);
        cvEmployeeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminHomeFragment.this, AdminEmployeeList.class);
                startActivity(intent);
            }
        });

        CardView cvTask = rootView.findViewById(R.id.card_view_task);
        ;
        cvTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminHomeFragment.this, AdminTaskList.class);
                startActivity(intent);
            }
        });

        CardView cvReport = rootView.findViewById(R.id.card_view_report);
        cvReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminHomeFragment.this, AdminReportList.class);
                startActivity(intent);
            }
        });
    }
}