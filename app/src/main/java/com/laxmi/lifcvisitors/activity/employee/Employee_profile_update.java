package com.laxmi.lifcvisitors.activity.employee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.laxmi.lifcvisitors.R;
import com.laxmi.lifcvisitors.activity.guard.GuardProfileActivity;
import com.laxmi.lifcvisitors.model.Profile;
import com.laxmi.lifcvisitors.retrofitservices.APIService;
import com.laxmi.lifcvisitors.retrofitservices.ApiClient;
import com.laxmi.lifcvisitors.savedata.PrefConfig;

import retrofit2.Call;
import retrofit2.Callback;

public class Employee_profile_update extends AppCompatActivity {

    EditText et_name, et_email,  et_mobile,et_role, et_department;
   Button btn_submit;
    PrefConfig prefConfig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_employee_profile_update);
        ImageView btn_save = findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        prefConfig = new PrefConfig(this);

      et_name = findViewById(R.id.ev_enter_names);
        et_email = findViewById(R.id.ev_emails);
        et_mobile = findViewById(R.id.ev_mobile_nos);
        et_role = findViewById(R.id.ev_roles);
        et_department = findViewById(R.id.ev_departments);
        btn_submit = findViewById(R.id.btn_submit);

        getProfile();
        btn_submit.setOnClickListener(view -> {
            if (et_name.getText().toString().equalsIgnoreCase("")
                    && et_email.getText().toString().equalsIgnoreCase("")
                    && et_mobile.getText().toString().equalsIgnoreCase("")) {
                Toast.makeText(this, "Enter Empty Fields", Toast.LENGTH_SHORT).show();
            } else {
                getProfileUpdate(et_name.getText().toString(), et_email.getText().toString(), et_mobile.getText().toString());
            }
        });

    }
    private void getProfile() {
        APIService service = ApiClient.getClient().create(APIService.class);
        Call<Profile> call = service.getProfile("Bearer " + prefConfig.readToken());
        call.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(@NonNull Call<Profile> call, @NonNull retrofit2.Response<Profile> response) {

                if (response.body() != null) {
                    if (response.body().getMessage().equalsIgnoreCase("Profile Fetched")) {

                        Log.d("Profile", "" + response.body().getData());

                        Profile.Data dataList = response.body().getData();

                        et_mobile.setText(dataList.getMobileNumber());
                        et_role.setText(dataList.getRole());
                        et_name.setText(dataList.getName());
                        et_email.setText(dataList.getEmail());
                        et_department.setText(dataList.getDepartment());

                    }
                } else {
                    Toast.makeText(Employee_profile_update.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(@NonNull Call<Profile> call, @NonNull Throwable t) {

                Log.d("Error", t.getMessage());
            }
        });
    }
    private void getProfileUpdate(String name, String email, String mobile) {
        APIService service = ApiClient.getClient().create(APIService.class);
        Call<Profile> call = service.getProfileUpdate("Bearer " + prefConfig.readToken(), mobile, name, email);
        call.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(@NonNull Call<Profile> call, @NonNull retrofit2.Response<Profile> response) {

                if (response.body() != null) {
                    if (response.body().getMessage().equalsIgnoreCase("Profile Updated Successfully")) {

                        Toast.makeText(Employee_profile_update.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                        finish();

                    }
                } else {
                    Toast.makeText(Employee_profile_update.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(@NonNull Call<Profile> call, @NonNull Throwable t) {

                Log.d("Error", t.getMessage());
            }
        });
    }

}