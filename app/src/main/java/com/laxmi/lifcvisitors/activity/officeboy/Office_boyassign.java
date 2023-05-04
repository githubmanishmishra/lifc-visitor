package com.laxmi.lifcvisitors.activity.officeboy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.laxmi.lifcvisitors.R;
import com.laxmi.lifcvisitors.activity.guard.GuardDashboard;
import com.laxmi.lifcvisitors.model.MSG;
import com.laxmi.lifcvisitors.retrofitservices.APIService;
import com.laxmi.lifcvisitors.retrofitservices.ApiClient;
import com.laxmi.lifcvisitors.savedata.PrefConfig;

import retrofit2.Call;
import retrofit2.Callback;

public class Office_boyassign extends AppCompatActivity {

    TextInputEditText ev_officeBoy_name;
    TextInputEditText mobile_no;

    TextView tv_submit;

    PrefConfig prefConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_office_boyassign);
        ImageView iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        prefConfig = new PrefConfig(this);
        ev_officeBoy_name = findViewById(R.id.ev_officeBoy_name);
        mobile_no = findViewById(R.id.mobile_no);
        tv_submit = findViewById(R.id.tv_submit);

        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            String Status = mBundle.getString("Status");
            int visitorId = mBundle.getInt("visitorId");
            String employeeId = mBundle.getString("employeeId");
            String sender = mBundle.getString("sender");
            String title = mBundle.getString("title");

          //  ev_officeBoy_name.setText(sender);
            //mobile_no.setText(title);


            tv_submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getOfficeBoyAssignApi(Status, visitorId,employeeId,sender,title);
                    //   Toast.makeText(Office_boyassign.this, "Office Boy Assigned", Toast.LENGTH_SHORT).show();

                }
            });
        }
        TextView tv = this.findViewById(R.id.mywidget);
        tv.setSelected(true);
    }

    private void getOfficeBoyAssignApi(String status, int visitorId, String employeeId,String sender,String title) {

        Log.d("fvvvvvvvvvvv",status+" "+ visitorId+" "+sender+" "+title);

        APIService service = ApiClient.getClient().create(APIService.class);
        Call<MSG> call = service.getOfficeBoyAssign("Bearer " + prefConfig.readToken(),
                visitorId, "", sender, title, "");
        call.enqueue(new Callback<MSG>() {
            @Override
            public void onResponse(@NonNull Call<MSG> call, @NonNull retrofit2.Response<MSG> response) {

                if (response.body() != null) {
                    //  pDialog.dismiss();
                    if (response.body().getMessage().equalsIgnoreCase("Office Boy registed successfully")) {

                        Toast.makeText(Office_boyassign.this, "Success", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Office_boyassign.this, GuardDashboard.class));
                        finish();
                    }

                } else {
                    Toast.makeText(Office_boyassign.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(@NonNull Call<MSG> call, @NonNull Throwable t) {

                // pDialog.dismiss();
                Log.d("Error", t.getMessage());
            }
        });

    }
}