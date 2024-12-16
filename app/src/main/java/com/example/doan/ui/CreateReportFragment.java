package com.example.doan.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.doan.R;

public class CreateReportFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_a_report, container, false);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);

        // Hiển thị nút back
        if (((AppCompatActivity) requireActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) requireActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.getNavigationIcon().setTint(getResources().getColor(android.R.color.white));
        toolbar.setNavigationOnClickListener(v -> navigateBackToReportLookUp());
        return view;
    }
    private void navigateBackToReportLookUp() {
        ReportLookupFragment reportLookupFragment = new ReportLookupFragment();
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout3, reportLookupFragment) // `fragment_container` là ID của container chứa Fragment
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null)
                .commit();
    }
}
