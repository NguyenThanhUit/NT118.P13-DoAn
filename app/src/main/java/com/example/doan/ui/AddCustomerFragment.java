package com.example.doan;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

public class AddCustomerFragment extends Fragment {

    private EditText edtMaKH, edtName, edtPhone, edtAddress;
    private TextView tvCustomerType;
    private Button btnSave;
    private com.example.doan.DatabaseHelper databaseHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_customer, container, false);

        // Khởi tạo các view
        edtMaKH = view.findViewById(R.id.edtMaKH);
        edtName = view.findViewById(R.id.edtName);
        edtPhone = view.findViewById(R.id.edtPhone);
        edtAddress = view.findViewById(R.id.edtAddress);
        tvCustomerType = view.findViewById(R.id.tvCustomerType);
        btnSave = view.findViewById(R.id.btnSave);

        // Khởi tạo DatabaseHelper
        databaseHelper = new com.example.doan.DatabaseHelper(getActivity(), "ContactsDB", null, 1);

        // Xử lý sự kiện khi nhấn nút Lưu
        // btnSave.setOnClickListener(v -> saveCustomer());

        // Gọi hàm hiển thị Dialog khi nhấn vào tvCustomerType
        tvCustomerType.setOnClickListener(v -> showCustomerTypeDialog());
        return view;
    }

    private void saveCustomer() {
        String maKH = edtMaKH.getText().toString().trim();
        String name = edtName.getText().toString().trim();
        String phone = edtPhone.getText().toString().trim();
        String address = edtAddress.getText().toString().trim();

        // Mở cơ sở dữ liệu để ghi
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        String insertSQL = "INSERT INTO Contacts (MAKH, NAME, PHONENUMBER, ADDRESS, status) VALUES (?, ?, ?, ?, ?)";

        try {
            db.execSQL(insertSQL, new String[]{maKH, name, phone, address});
            Toast.makeText(getActivity(), "Khách hàng đã được thêm thành công!", Toast.LENGTH_SHORT).show();
            getActivity().getSupportFragmentManager().popBackStack(); // Quay lại fragment trước đó
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Lỗi khi thêm khách hàng!", Toast.LENGTH_SHORT).show();
        } finally {
            db.close();
        }
    }

    // Hàm riêng để hiển thị Dialog chọn loại khách hàng
    private void showCustomerTypeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Chọn loại khách hàng");

        // Danh sách các loại khách hàng
        String[] customerTypes = {"Mới", "Chưa tiếp cận", "Tiếp cận", "Nóng", "Tiềm năng"};

        // Thiết lập danh sách các loại khách hàng trong Dialog
        builder.setItems(customerTypes, (dialog, which) -> {
            // Cập nhật lựa chọn vào TextView
            tvCustomerType.setText(customerTypes[which]);
        });

        // Hiển thị Dialog
        builder.create().show();
    }
}
