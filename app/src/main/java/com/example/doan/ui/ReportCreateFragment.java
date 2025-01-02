package com.example.doan.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public class ReportCreateFragment extends Fragment {
    private ImageButton btnBack;
    private EditText etTitle, etStartDate, etDueDate, etDescription;
    private Button btnCreate;
    private ReportsViewModel reportsViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.report_create, container, false);

        // Ánh xạ các thành phần từ XML
        etTitle = view.findViewById(R.id.et_report_title);
        etStartDate = view.findViewById(R.id.et_report_start);
        etDueDate = view.findViewById(R.id.et_report_end);
        etDescription = view.findViewById(R.id.et_report_description);
        btnCreate = view.findViewById(R.id.btn_create_report);
        btnBack = view.findViewById(R.id.ic_back);

        // Khởi tạo ViewModel
        reportsViewModel = new ViewModelProvider(requireActivity()).get(ReportsViewModel.class);

        // Xử lý sự kiện nhấn nút "Create"
        btnCreate.setOnClickListener(view1 -> {
            String title = etTitle.getText().toString().trim();
            String startDate = etStartDate.getText().toString().trim();
            String endDate = etDueDate.getText().toString().trim();
            String description = etDescription.getText().toString().trim();

            if (TextUtils.isEmpty(title) || TextUtils.isEmpty(startDate) || TextUtils.isEmpty(endDate) || TextUtils.isEmpty(description)) {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            Reports newReport = new Reports(
                    String.valueOf(System.currentTimeMillis()), // ID tự tạo
                    title,
                    description,
                    startDate,
                    endDate
            );

            reportsViewModel.addnewReport(newReport);
            Toast.makeText(requireContext(), "Report created successfully!", Toast.LENGTH_SHORT).show();

            // Quay lại màn hình trước đó
            requireActivity().getSupportFragmentManager().popBackStack();
        });

        // Xử lý sự kiện nhấn nút "Back"
        btnBack.setOnClickListener(view12 -> requireActivity().getSupportFragmentManager().popBackStack());

        return view;
    }
}