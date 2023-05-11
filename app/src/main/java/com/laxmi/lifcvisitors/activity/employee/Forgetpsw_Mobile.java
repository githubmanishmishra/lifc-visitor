package com.laxmi.lifcvisitors.activity.employee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;
import com.laxmi.lifcvisitors.R;
import com.laxmi.lifcvisitors.model.MSG;
import com.laxmi.lifcvisitors.retrofitservices.APIService;
import com.laxmi.lifcvisitors.retrofitservices.ApiClient;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Forgetpsw_Mobile extends AppCompatActivity {
    Intent intent;
    TextView tv_getotp;
    TextInputEditText mobileNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpsw);
        tv_getotp = findViewById(R.id.tv_getotp);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
         mobileNo = findViewById(R.id.mobile_no);
        ImageView iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        tv_getotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validate()) {
                    onUpdateFailed();
                } else {
                    otpApi();
                   /* Intent intent = new Intent(Forgetpsw_Mobile.this, Employeeotpverification.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("mob_no", mobileNo.getText().toString());
                    intent.putExtras(bundle);
                    startActivity(intent);*/
                }

            }
        });

        TextView tv = (TextView) this.findViewById(R.id.mywidget);
        tv.setSelected(true);
    }
    private boolean validate() {
        boolean valid = true;
        String mobileNos = Objects.requireNonNull(mobileNo.getText()).toString();
        if (mobileNos.isEmpty() | mobileNos.length()!=10) {
            mobileNo.setError("Enter Valid Mobile Number ");
            requestFocus(mobileNo);
            valid = false;
        }
        else {
            mobileNo.setError(null);
        }
        return valid;
    }
    private void onUpdateFailed() {

    }
    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private void otpApi() {
        APIService service = ApiClient.getClient().create(APIService.class);

        Call<MSG> call = service.getOtpForgotPassword(mobileNo.getText().toString(),"forget");
        call.enqueue(new Callback<MSG>() {
            @Override
            public void onResponse(@NonNull Call<MSG> call, @NonNull Response<MSG> response) {
                if( response.body()!=null){
                    Toast.makeText(Forgetpsw_Mobile.this, "success", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(Forgetpsw_Mobile.this, Employeeotpverification.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("mob_no", mobileNo.getText().toString());
                    bundle.putString("otpValue", response.body().getOtp());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else {
                    Toast.makeText(Forgetpsw_Mobile.this, "Invalid Otp", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<MSG> call, Throwable t) {

                // pDialog.dismiss();
                //  Log.d("Error", t.getMessage());
            }
        });
    }
}