package com.laxmi.lifcvisitors.activity.guard;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.laxmi.lifcvisitors.R;
import com.laxmi.lifcvisitors.model.Profile;
import com.laxmi.lifcvisitors.retrofitservices.APIService;
import com.laxmi.lifcvisitors.retrofitservices.ApiClient;
import com.laxmi.lifcvisitors.savedata.PrefConfig;

import retrofit2.Call;
import retrofit2.Callback;

public class GuardProfileActivity extends AppCompatActivity {

    EditText et_name, et_email, et_mobile, et_role, et_reporting_branch;
    Button btn_submit;
    PrefConfig prefConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        prefConfig = new PrefConfig(this);

        et_name = findViewById(R.id.et_name);
        et_email = findViewById(R.id.et_email);
        et_mobile = findViewById(R.id.et_mobile);
        et_role = findViewById(R.id.et_role);
        et_reporting_branch = findViewById(R.id.et_reporting_branch);
        btn_submit = findViewById(R.id.btn_submit);

        String name = et_name.getText().toString().trim();
        String email = et_email.getText().toString().trim();
        String mobile = et_mobile.getText().toString().trim();
        String role = et_role.getText().toString().trim();
        String reportingBranch = et_reporting_branch.getText().toString().trim();

        btn_submit.setOnClickListener(view -> {
            if (name.isEmpty() && email.isEmpty() && mobile.isEmpty() && role.isEmpty() && reportingBranch.isEmpty()) {
                Toast.makeText(this, "Enter Empty Fields", Toast.LENGTH_SHORT).show();
            } else {
                getProfile();
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

                    }
                } else {
                    Toast.makeText(GuardProfileActivity.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(@NonNull Call<Profile> call, @NonNull Throwable t) {

                Log.d("Error", t.getMessage());
            }
        });
    }
}