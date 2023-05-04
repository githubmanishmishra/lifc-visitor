package com.laxmi.lifcvisitors.activity.employee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;
import com.laxmi.lifcvisitors.R;

import java.util.Objects;

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
                    Intent intent = new Intent(Forgetpsw_Mobile.this, Employeeotpverification.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("mob_no", mobileNo.getText().toString());
                    intent.putExtras(bundle);
                    startActivity(intent);
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


}