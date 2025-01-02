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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan.MainActivity;
import com.example.doan.MainActivityForSaleEmployee;
import com.example.doan.R;
import com.example.doan.customers.AddNewCustomerActivity;
import com.example.doan.reports.EmployeeReportAdapter;
import com.example.doan.reports.Reports;
import com.example.doan.reports.ReportsViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private TextView textViewName;
    private ReportsViewModel reportsViewModel;
    private RecyclerView recyclerView;
    private EmployeeReportAdapter reportsAdapter; // Adapter cho RecyclerView

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        textViewName = view.findViewById(R.id.tvTenNV);
        recyclerView = view.findViewById(R.id.recycler_view_notifications);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        reportsAdapter = new EmployeeReportAdapter(); // Khởi tạo adapter
        recyclerView.setAdapter(reportsAdapter);

        // Khởi tạo ViewModel
        reportsViewModel = new ViewModelProvider(this).get(ReportsViewModel.class);

        // Quan sát LiveData từ ViewModel
        reportsViewModel.getAllreports().observe(getViewLifecycleOwner(), new Observer<List<Reports>>() {
            @Override
            public void onChanged(List<Reports> reports) {
                // Cập nhật dữ liệu cho Adapter khi có thay đổi
                reportsAdapter.submitList(reports);
            }
        });

        // Thiết lập sự kiện click cho item
        reportsAdapter.setOnItemClickListener(report -> {
            ReportDetailEmployeeFragment fragment = new ReportDetailEmployeeFragment();

            Bundle bundle = new Bundle();
            bundle.putString("id", report.getReportID());
            bundle.putString("title", report.getReportTitle());
            bundle.putString("startDate", report.getStartDate());
            bundle.putString("endDate", report.getEndDate());
            bundle.putString("description", report.getReportContent());
            fragment.setArguments(bundle);

            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameLayout4, fragment)
                    .addToBackStack(null)
                    .commit();
        });

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

