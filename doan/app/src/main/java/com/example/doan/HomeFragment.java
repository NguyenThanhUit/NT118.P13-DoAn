package com.example.doan;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private DBAdapter dbAdapter;
    private List<Contacts> contactsData;

    private int countAll, countNew, countNotApproach, countApproach, countHot, countPotential;
    private TextView selectedTextView; // Lưu trữ TextView được chọn hiện tại
    private TextView tvFilterAll, tvFilterNew, tvFilterApproach, tvFilterNotApproach, tvFilterHot, tvFilterPotential;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbAdapter = new DBAdapter(getContext());
        dbAdapter.open();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        dbAdapter = new DBAdapter(getContext());
        dbAdapter.open();

        // Tìm các TextView
        tvFilterAll = view.findViewById(R.id.tvFilterAll);
        tvFilterNew = view.findViewById(R.id.tvFilterNew);
        tvFilterApproach = view.findViewById(R.id.tvFilterApproach);
        tvFilterNotApproach = view.findViewById(R.id.tvFilterNotApproach);
        tvFilterHot = view.findViewById(R.id.tvFilterHot);
        tvFilterPotential = view.findViewById(R.id.tvFilterPotential);

        // Đặt sự kiện nhấn cho mỗi TextView
        TextView[] filters = {tvFilterAll, tvFilterNew, tvFilterApproach, tvFilterNotApproach, tvFilterHot, tvFilterPotential};
        for (TextView filter : filters) {
            filter.setOnClickListener(v -> onFilterClicked(filter));
        }
        // Lấy số lượng khách hàng cho từng bộ lọc và cập nhật TextView
        updateFilterCounts();


        return view;
    }

    // Phương thức thay đổi trạng thái khi Button được chọn
    private void onFilterClicked(TextView newSelectedTextView) {
        /// Đặt lại trạng thái của TextView trước đó nếu có
        if (selectedTextView != null) {
            selectedTextView.setSelected(false);
            selectedTextView.setTextColor(getResources().getColor(R.color.black));
        }

        // Đặt trạng thái được chọn cho TextView mới
        newSelectedTextView.setSelected(true);
        newSelectedTextView.setTextColor(getResources().getColor(R.color.blue_dark));
        selectedTextView = newSelectedTextView;
    }

    private void updateFilterCounts() {
        // Lấy số lượng khách hàng cho từng bộ lọc
        countAll = dbAdapter.getCountByFilter("Tất cả");
        countNew = dbAdapter.getCountByFilter("Mới");
        countNotApproach = dbAdapter.getCountByFilter("Chưa tiếp");
        countApproach = dbAdapter.getCountByFilter("Tiếp cận");
        countHot = dbAdapter.getCountByFilter("Nóng");
        countPotential = dbAdapter.getCountByFilter("Tiềm năng");

        // Cập nhật số lượng vào các TextView với cấu trúc hiển thị như trong ảnh
        tvFilterAll.setText(" Tất cả " + formatCount(countAll));
        tvFilterNew.setText(" Mới " + formatCount(countNew));
        tvFilterNotApproach.setText(" Chưa tiếp cận " + formatCount(countNotApproach));
        tvFilterApproach.setText(" Tiếp cận " + formatCount(countApproach));
        tvFilterHot.setText(" Nóng " + formatCount(countHot));
        tvFilterPotential.setText(" Tiềm năng " + formatCount(countPotential));
    }

    private String formatCount(int count) {
        return "(" + (count > 0 ? String.valueOf(count) : "0") + ")";
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        dbAdapter.close(); // Đóng kết nối khi view bị hủy
    }
}