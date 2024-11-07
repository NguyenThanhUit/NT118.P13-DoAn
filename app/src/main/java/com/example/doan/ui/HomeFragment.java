package com.example.doan.ui;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.doan.R;
import com.example.doan.data.DBAdapter;
import com.example.doan.domain.contact.ContactDto;

import java.util.List;


public class HomeFragment extends Fragment {

    private DBAdapter dbAdapter;
    private List<ContactDto> contactsData;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        dbAdapter = new DBAdapter(getContext());
        dbAdapter.open();
        dbAdapter.deleteAllUsers();

        return view;
    }
}