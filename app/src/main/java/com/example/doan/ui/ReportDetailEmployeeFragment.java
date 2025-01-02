package com.example.doan.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.doan.R;
import com.example.doan.reports.Reports;
import com.example.doan.reports.ReportsViewModel;

public class ReportDetailEmployeeFragment extends Fragment {

    private TextView tvReportTitle, tvReportStartDate, tvReportEndDate, tvReportDescription;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reports_info, container, false);

        // Ánh xạ view
        tvReportTitle = view.findViewById(R.id.tv_report_title);
        tvReportStartDate = view.findViewById(R.id.tv_report_start_date);
        tvReportEndDate = view.findViewById(R.id.tv_report_end_date);
        tvReportDescription = view.findViewById(R.id.tv_report_description);

        // Nhận dữ liệu từ Bundle
        if (getArguments() != null) {
            tvReportTitle.setText(getArguments().getString("title", "No Title"));
            tvReportStartDate.setText("Start Date: " + getArguments().getString("startDate", "N/A"));
            tvReportEndDate.setText("End Date: " + getArguments().getString("endDate", "N/A"));
            tvReportDescription.setText("Description: " + getArguments().getString("description", "No Description"));
        }

        // Nhận dữ liệu từ ViewModel
        ReportsViewModel viewModel = new ViewModelProvider(requireActivity()).get(ReportsViewModel.class);
        String reportId = getArguments() != null ? getArguments().getString("id") : null;

        viewModel.getAllreports().observe(getViewLifecycleOwner(), reports -> {
            if (reports != null && reportId != null) {
                for (Reports report : reports) {
                    if (report.getReportID().equals(reportId)) {
                        tvReportTitle.setText(report.getReportTitle());
                        tvReportStartDate.setText("Start Date: " + report.getStartDate());
                        tvReportEndDate.setText("End Date: " + report.getEndDate());
                        tvReportDescription.setText("Description: " + report.getReportContent());
                        break;
                    }
                }
            }
        });

        // Xử lý nút quay lại
        ImageButton icBack = view.findViewById(R.id.ic_back);
        icBack.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStack());

        return view;
    }
}
