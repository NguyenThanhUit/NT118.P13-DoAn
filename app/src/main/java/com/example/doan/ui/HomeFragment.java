package com.example.doan.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.example.doan.R;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        // Lấy các CardView
        CardView cardTaskLookup = view.findViewById(R.id.card_view_task);
        CardView cardReportLookup = view.findViewById(R.id.card_view_report);

        // Xử lý sự kiện nhấn cho "Tra cứu nhiệm vụ"
        cardTaskLookup.setOnClickListener(v -> replaceFragment(new TaskLookupFragment()));

        // Xử lý sự kiện nhấn cho "Tra cứu báo cáo"
        cardReportLookup.setOnClickListener(v -> replaceFragment(new ReportLookupFragment()));

        return view;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout3, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
