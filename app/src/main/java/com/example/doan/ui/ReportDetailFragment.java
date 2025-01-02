package com.example.doan.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.doan.R;
import com.example.doan.reports.Reports;
import com.example.doan.reports.ReportsViewModel;

public class ReportDetailFragment extends Fragment {

    private TextView tvReportTitle, tvReportStartDate, tvReportEndDate, tvReportAuthor, tvReportDescription;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin_reports_infor, container, false);

        // Ánh xạ view
        tvReportTitle = view.findViewById(R.id.tv_report_title);
        tvReportStartDate = view.findViewById(R.id.tv_report_start_date);
        tvReportEndDate = view.findViewById(R.id.tv_report_end_date);
        tvReportAuthor = view.findViewById(R.id.tv_report_author);
        tvReportDescription = view.findViewById(R.id.tv_report_description);

        // Nhận dữ liệu từ Bundle
        if (getArguments() != null) {
            tvReportTitle.setText(getArguments().getString("title", "No Title"));
            tvReportStartDate.setText("Start Date: " + getArguments().getString("startDate", "N/A"));
            tvReportEndDate.setText("End Date: " + getArguments().getString("endDate", "N/A"));
            tvReportAuthor.setText("Reported by: " + getArguments().getString("author", "Unknown"));
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

        // Xử lý nút chỉnh sửa
        ImageButton icEdit = view.findViewById(R.id.ic_edit);
        icEdit.setOnClickListener(v -> {
            EditReportFragment fragment = new EditReportFragment();

            Bundle bundle = new Bundle();
            bundle.putString("id", getArguments().getString("id"));
            bundle.putString("title", getArguments().getString("title"));
            bundle.putString("startDate", getArguments().getString("startDate"));
            bundle.putString("endDate", getArguments().getString("endDate"));
            bundle.putString("description", getArguments().getString("description"));
            fragment.setArguments(bundle);

            // Chuyển đến Fragment Edit
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameLayout3, fragment)
                    .addToBackStack(null)
                    .commit();
        });


        return view;
    }
}