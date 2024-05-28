package com.example.travelbuddy.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.example.travelbuddy.Models.UserModel;
import com.example.travelbuddy.Models.UserPreference;
import com.example.travelbuddy.R;

public class FormUserActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtName, edtNim, edtPhone, edtAge;

    public static final String EXTRA_TYPE_FORM = "extra_type_form";
    public final static String EXTRA_RESULT = "extra_result";
    public static final int RESULT_CODE = 101;
    public static final int TYPE_ADD = 1;
    public static final int TYPE_EDIT = 2;
    private final String FIELD_REQUIRED = "Field tidak boleh kosong";
    private final String FIELD_DIGIT_ONLY = "Hanya boleh terisi numerik";
    private UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_user);

        edtName = findViewById(R.id.edt_name);
        edtAge = findViewById(R.id.edt_age);
        edtNim = findViewById(R.id.edt_nim);
        edtPhone = findViewById(R.id.edt_phone);
        Button btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(this);

        userModel = new UserModel();

        userModel = getIntent().getParcelableExtra("USER");
        int formType = getIntent().getIntExtra(EXTRA_TYPE_FORM, 0);
        String actionBarTitle = "";
        String btnTitle = "";
        switch (formType) {
            case TYPE_ADD:
                actionBarTitle = "Tambah Baru";
                btnTitle = "Simpan";
                break;
            case TYPE_EDIT:
                actionBarTitle = "Ubah";
                btnTitle = "Update";
                showPreferenceInForm();
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(actionBarTitle);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        btnSave.setText(btnTitle);

    }

    private void showPreferenceInForm() {
        edtName.setText(userModel.getName());
        edtNim.setText(userModel.getNim());
        edtAge.setText(String.valueOf(userModel.getAge()));
        edtPhone.setText(userModel.getPhoneNumber());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_save) {
            String name = edtName.getText().toString().trim();
            String nim = edtNim.getText().toString().trim();
            String age = edtAge.getText().toString().trim();
            String phoneNo = edtPhone.getText().toString().trim();

            if (TextUtils.isEmpty(name)) {
                edtName.setError(FIELD_REQUIRED);
                return;
            }

            if (TextUtils.isEmpty(nim)) {
                edtNim.setError(FIELD_REQUIRED);
                return;
            }

            if (TextUtils.isEmpty(age)) {
                edtAge.setError(FIELD_REQUIRED);
                return;
            }

            if (TextUtils.isEmpty(phoneNo)) {
                edtPhone.setError(FIELD_REQUIRED);
                return;
            }

            if (!TextUtils.isDigitsOnly(phoneNo)) {
                edtPhone.setError(FIELD_DIGIT_ONLY);
                return;
            }

            saveUser(name, nim, age, phoneNo);

            Intent resultIntent = new Intent();
            resultIntent.putExtra(EXTRA_RESULT, userModel);
            setResult(RESULT_CODE, resultIntent);
            finish();
        }
    }

    private void saveUser(String name, String nim, String age, String phoneNo) {
        UserPreference userPreference = new UserPreference(this);
        userModel.setName(name);
        userModel.setNim(nim);
        userModel.setAge(Integer.parseInt(age));
        userModel.setPhoneNumber(phoneNo);
        userPreference.setUser(userModel);
        Toast.makeText(this, "Data tersimpan", Toast.LENGTH_SHORT).show();
    }


}
