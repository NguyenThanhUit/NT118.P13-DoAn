package com.example.doan.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.doan.R;
import com.example.doan.reports.Reports;
import com.example.doan.reports.ReportsViewModel;

public class EditReportFragment extends Fragment {

    private EditText etReportTitle, etReportStart, etReportEnd, etReportDescription;
    private ReportsViewModel reportsViewModel;
    private String reportId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin_reports_edit, container, false);

        // Ánh xạ view
        etReportTitle = view.findViewById(R.id.et_report_title);
        etReportStart = view.findViewById(R.id.et_report_start);
        etReportEnd = view.findViewById(R.id.et_report_end);
        etReportDescription = view.findViewById(R.id.et_report_description);

        reportsViewModel = new ViewModelProvider(this).get(ReportsViewModel.class);

        // Nhận dữ liệu từ Bundle
        if (getArguments() != null) {
            reportId = getArguments().getString("id");
            etReportTitle.setText(getArguments().getString("title", ""));
            etReportStart.setText(getArguments().getString("startDate", ""));
            etReportEnd.setText(getArguments().getString("endDate", ""));
            etReportDescription.setText(getArguments().getString("description", ""));
        }

        // Xử lý nút lưu
        ImageButton icDone = view.findViewById(R.id.ic_done);
        icDone.setOnClickListener(v -> saveChanges());

        return view;
    }

    private void saveChanges() {
        String title = etReportTitle.getText().toString().trim();
        String startDate = etReportStart.getText().toString().trim();
        String endDate = etReportEnd.getText().toString().trim();
        String description = etReportDescription.getText().toString().trim();

        if (!title.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
            Reports updatedReport = new Reports(reportId, title, description, startDate, endDate);
            reportsViewModel.updateReport(updatedReport);

            // Quay lại màn hình trước đó
            requireActivity().getSupportFragmentManager().popBackStack();
        } else {
            Toast.makeText(requireContext(), "Please fill all fields!", Toast.LENGTH_SHORT).show();
        }
    }
}