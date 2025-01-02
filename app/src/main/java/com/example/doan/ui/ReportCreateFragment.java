package com.example.doan.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.doan.R;
import com.example.doan.reports.Reports;
import com.example.doan.reports.ReportsViewModel;

import java.util.Calendar;

public class ReportCreateFragment extends Fragment {
    private ImageButton btnBack;
    private EditText etTitle, etDescription;
    private Button btnCreate;
    private ReportsViewModel reportsViewModel;
    private TextView tvStartDateLabel, tvEndDateLabel;
    private Button btnSelectStartDate, btnSelectEndDate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.report_create, container, false);

        // Ánh xạ các thành phần từ XML
        etTitle = view.findViewById(R.id.et_report_title);
        tvStartDateLabel = view.findViewById(R.id.tv_start_date_label);
        tvEndDateLabel = view.findViewById(R.id.tv_end_date_label);
        btnSelectStartDate = view.findViewById(R.id.btn_select_start_date);
        btnSelectEndDate = view.findViewById(R.id.btn_select_end_date);
        etDescription = view.findViewById(R.id.et_report_description);
        btnCreate = view.findViewById(R.id.btn_create_report);
        btnBack = view.findViewById(R.id.ic_back);

        // Khởi tạo ViewModel
        reportsViewModel = new ViewModelProvider(requireActivity()).get(ReportsViewModel.class);

        // Xử lý chọn ngày bắt đầu
        btnSelectStartDate.setOnClickListener(v -> showDatePickerDialog(date -> tvStartDateLabel.setText(date)));

        // Xử lý chọn ngày kết thúc
        btnSelectEndDate.setOnClickListener(v -> showDatePickerDialog(date -> tvEndDateLabel.setText(date)));

        // Xử lý sự kiện nhấn nút "Create"
        btnCreate.setOnClickListener(view1 -> {
            String title = etTitle.getText().toString().trim();
            String startDate = tvStartDateLabel.getText().toString().trim();
            String endDate = tvEndDateLabel.getText().toString().trim();
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

    private void showDatePickerDialog(OnDateSelectedListener listener) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(),
                (view, year, month, dayOfMonth) -> {
                    // Định dạng ngày: dd/MM/yyyy
                    String date = String.format("%02d/%02d/%04d", dayOfMonth, month + 1, year);
                    listener.onDateSelected(date);
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    // Interface để callback ngày đã chọn
    private interface OnDateSelectedListener {
        void onDateSelected(String date);
    }
}
