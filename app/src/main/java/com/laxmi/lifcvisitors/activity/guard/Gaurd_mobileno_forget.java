package com.laxmi.lifcvisitors.activity.guard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.laxmi.lifcvisitors.R;
import com.laxmi.lifcvisitors.activity.employee.Employeeotpverification;
import com.laxmi.lifcvisitors.activity.employee.Forgetpsw_Mobile;
import com.laxmi.lifcvisitors.model.MSG;
import com.laxmi.lifcvisitors.retrofitservices.APIService;
import com.laxmi.lifcvisitors.retrofitservices.ApiClient;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Gaurd_mobileno_forget extends AppCompatActivity {
    TextView confirmpsw;
    Intent intent;
    TextInputEditText textInputMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaurd_mobileno_forget);
        ImageView iv_back = findViewById(R.id.iv_back);
        confirmpsw = findViewById(R.id.tv_confirmpsw);
        textInputMobile = findViewById(R.id.mobile_no);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        confirmpsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             /*   if(Objects.requireNonNull(textInputMobile.getText()).toString().isEmpty() &&
                        textInputMobile.getText().toString().length()!=10){
                    Toast.makeText(Gaurd_mobileno_forget.this, "Enter 10 digit mobile number", Toast.LENGTH_SHORT).show();
                }*/
                if (!validate()) {
                    onUpdateFailed();
                } else {/*
                    Intent intent = new Intent(Gaurd_mobileno_forget.this, GuardForgetOtpVerification.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("mob_no", textInputMobile.getText().toString());
                    intent.putExtras(bundle);
                    startActivity(intent);*/
                    otpApi();
                }

            }
        });
        TextView tv = (TextView) this.findViewById(R.id.mywidget);
        tv.setSelected(true);

    }



    private boolean validate() {
        boolean valid = true;
        String mobileNo = Objects.requireNonNull(textInputMobile.getText()).toString();
        if (mobileNo.isEmpty() | mobileNo.length()!=10) {
            textInputMobile.setError("Enter Valid Mobile Number ");
            requestFocus(textInputMobile);
            valid = false;
        }
        else {
            textInputMobile.setError(null);
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

        Call<MSG> call = service.getOtpForgotPassword(textInputMobile.getText().toString(),"forget");
        call.enqueue(new Callback<MSG>() {
            @Override
            public void onResponse(@NonNull Call<MSG> call, @NonNull Response<MSG> response) {
                if( response.body()!=null){
                    Toast.makeText(Gaurd_mobileno_forget.this, "success", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(Gaurd_mobileno_forget.this, GuardForgetOtpVerification.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("mob_no", textInputMobile.getText().toString());
                    bundle.putString("otpValue", response.body().getOtp());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else {
                    Toast.makeText(Gaurd_mobileno_forget.this, "Invalid Otp", Toast.LENGTH_SHORT).show();
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