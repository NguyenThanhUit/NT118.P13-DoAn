package com.example.doan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import android.widget.Switch;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button btnNotifications;
    private boolean isNotificationEnabled = true;
    private Switch nightModeSwitch;
    // Khai báo nút "Log out"
    private Button btnLogout;
    public SettingsFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        // Lấy Switch từ layout
        nightModeSwitch = view.findViewById(R.id.night_mode_switch);

        // Kiểm tra chế độ hiện tại của ứng dụng và thiết lập trạng thái cho Switch
        SharedPreferences preferences = getActivity().getSharedPreferences("settings", getContext().MODE_PRIVATE);
        boolean nightMode = preferences.getBoolean("night_mode", false);

        // Cập nhật trạng thái cho Switch
        nightModeSwitch.setChecked(nightMode);

        // Lắng nghe sự thay đổi của Switch
        nightModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Lưu trạng thái vào SharedPreferences
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("night_mode", isChecked);
                editor.apply();

                // Chuyển đổi chế độ sáng/tối
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }

                // Áp dụng thay đổi chế độ ngay lập tức
                getActivity().recreate();
            }
        });
        // Tìm nút btn_notifications và thiết lập sự kiện chuyển đổi
        btnNotifications = view.findViewById(R.id.btn_notifications);
        btnNotifications.setOnClickListener(v -> toggleNotification());

        // Thiết lập Spinner với hai lựa chọn English và Tiếng Việt
        Spinner languageSpinner = view.findViewById(R.id.language_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getContext(),
                R.array.language_options,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageSpinner.setAdapter(adapter);

        // Find the "Create" button
        Button btnCreateAccount = view.findViewById(R.id.btn_createaccount);

        // Set an onClickListener on the button
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the CreateAccountActivity
                Intent intent = new Intent(getActivity(), CreateAccountActivity.class);
                startActivity(intent);
            }
        });
        // Ánh xạ nút "Log out"
        btnLogout = view.findViewById(R.id.btn_logout);
        // Gán sự kiện cho nút "Log out"
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Khi nhấn nút "Log out", mở LogInActivity
                Intent intent = new Intent(getActivity(), LogInActivity.class);
                startActivity(intent);
                getActivity().finish();  // Kết thúc SettingsActivity để không quay lại được
            }
        });
        return view;
    }
    private void toggleNotification() {
        isNotificationEnabled = !isNotificationEnabled;  // Đổi trạng thái
        btnNotifications.setText(isNotificationEnabled ? "Enabled" : "Disabled");  // Cập nhật văn bản nút
    }
}