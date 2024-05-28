package com.example.travelbuddy.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.travelbuddy.Activity.FormUserActivity;
import com.example.travelbuddy.Models.UserModel;
import com.example.travelbuddy.Models.UserPreference;
import com.example.travelbuddy.R;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    private TextView tvName, tvAge, tvPhoneNo, tvNim;
    private Button btnSave;
    private UserPreference mUserPreference;
    private boolean isPreferenceEmpty = false;
    private UserModel userModel;

    private final ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getData() != null && result.getResultCode() == FormUserActivity.RESULT_CODE) {
                    userModel = result.getData().getParcelableExtra(FormUserActivity.EXTRA_RESULT);
                    populateView(userModel);
                    checkForm(userModel);
                }
            }
    );

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        tvName = view.findViewById(R.id.tv_name);
        tvAge = view.findViewById(R.id.tv_age);
        tvPhoneNo = view.findViewById(R.id.tv_phone);
        tvNim = view.findViewById(R.id.tv_nim);
        btnSave = view.findViewById(R.id.btn_save);

        mUserPreference = new UserPreference(requireContext());
        showExistingPreference();

        btnSave.setOnClickListener(this);

        return view;
    }

    private void showExistingPreference() {
        userModel = mUserPreference.getUser();
        populateView(userModel);
        checkForm(userModel);
    }

    private void populateView(UserModel userModel) {
        tvName.setText(TextUtils.isEmpty(userModel.getName()) ? "Tidak Ada" : userModel.getName());
        tvAge.setText(userModel.getAge() == 0 ? "Tidak Ada" : String.valueOf(userModel.getAge()));
        tvNim.setText(TextUtils.isEmpty(userModel.getNim()) ? "Tidak Ada" : userModel.getNim());
        tvPhoneNo.setText(TextUtils.isEmpty(userModel.getPhoneNumber()) ? "Tidak Ada" : userModel.getPhoneNumber());
    }

    private void checkForm(UserModel userModel) {
        if (!TextUtils.isEmpty(userModel.getName())) {
            btnSave.setText(getString(R.string.change));
            isPreferenceEmpty = false;
        } else {
            btnSave.setText(getString(R.string.save));
            isPreferenceEmpty = true;
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_save) {
            Intent intent = new Intent(getActivity(), FormUserActivity.class);
            if (isPreferenceEmpty) {
                intent.putExtra(FormUserActivity.EXTRA_TYPE_FORM, FormUserActivity.TYPE_ADD);
            } else {
                intent.putExtra(FormUserActivity.EXTRA_TYPE_FORM, FormUserActivity.TYPE_EDIT);
            }
            intent.putExtra("USER", userModel);
            resultLauncher.launch(intent);
        }
    }
}
