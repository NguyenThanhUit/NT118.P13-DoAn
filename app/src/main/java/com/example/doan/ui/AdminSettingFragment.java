package com.example.doan.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.doan.R;
import com.example.doan.domain.auth.LogInActivity;
import com.example.doan.domain.employee.ManageEmployeeActivity;

public class AdminSettingFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.admin_settings_fragment, container, false);

        Button btnSecurity = view.findViewById(R.id.btn_security);
        btnSecurity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Notice")
                        .setMessage("Function is under development")
                        .setPositiveButton("OK", null)
                        .show();
            }
        });

        Button btnLanguage = view.findViewById(R.id.btn_language);
        btnLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Notice")
                        .setMessage("Function is under development")
                        .setPositiveButton("OK", null)
                        .show();
            }
        });

        Button reportProblem = view.findViewById(R.id.btn_report);
        reportProblem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Notice")
                        .setMessage("Function is under development")
                        .setPositiveButton("OK", null)
                        .show();
            }
        });





        Button btnCreateAccount = view.findViewById(R.id.btn_create_account);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), ManageEmployeeActivity.class);
                startActivity(i);
            }
        });

        Button btnLogout = view.findViewById(R.id.btn_log_out);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), LogInActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });



        return view;
    }

}
