package com.example.doan.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan.R;
import com.example.doan.reports.AdapterForReports;
import com.example.doan.reports.ReportsViewModel;
import com.example.doan.tasks.AssignTaskActivity;

public class AdminHomeFragment extends Fragment {
    private ReportsViewModel reportsViewModel;
    private AdapterForReports reportsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin_home_fragment, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_notifications);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        reportsAdapter = new AdapterForReports();
        recyclerView.setAdapter(reportsAdapter);

        reportsViewModel = new ViewModelProvider(requireActivity()).get(ReportsViewModel.class);

        // Lắng nghe dữ liệu thay đổi
        reportsViewModel.getAllreports().observe(getViewLifecycleOwner(), reports -> {
            reportsAdapter.setReportsList(reports);
        });

        // Xử lý khi nhấn lâu
        reportsAdapter.setOnReportLongClickListener(report -> {
            // Hiển thị Dialog xác nhận
            new AlertDialog.Builder(requireContext())
                    .setTitle("Xóa báo cáo")
                    .setMessage("Bạn có chắc muốn xóa báo cáo này?")
                    .setPositiveButton("Xóa", (dialog, which) -> {
                        reportsViewModel.deleteReport(report); // Xóa trong ViewModel
                    })
                    .setNegativeButton("Hủy", null)
                    .show();
        });

        // Click vào item
        reportsAdapter.setOnReportClickListener(report -> {
            ReportDetailFragment fragment = new ReportDetailFragment();

            // Gửi dữ liệu qua Bundle
            Bundle bundle = new Bundle();
            bundle.putString("id", report.getReportID());
            bundle.putString("title", report.getReportTitle());
            bundle.putString("startDate", report.getStartDate());
            bundle.putString("endDate", report.getEndDate());
            bundle.putString("description", report.getReportContent());
            fragment.setArguments(bundle);

            // Chuyển đến Fragment chi tiết
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameLayout3, fragment)
                    .addToBackStack(null)
                    .commit();
        });


        CardView cardViewCustomer = view.findViewById(R.id.card_view_employee);
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

        CardView cardViewReport = view.findViewById(R.id.card_view_report);
        cardViewReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReportCreateFragment fragment = new ReportCreateFragment();
                getParentFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayout3, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
        return view;
    }
}