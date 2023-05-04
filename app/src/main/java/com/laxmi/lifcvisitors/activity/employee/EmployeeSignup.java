package com.laxmi.lifcvisitors.activity.employee;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.view.WindowManager;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.messaging.FirebaseMessaging;
import com.laxmi.lifcvisitors.R;
import com.laxmi.lifcvisitors.activity.guard.GaurdLogin;
import com.laxmi.lifcvisitors.activity.guard.Gaurdotp_verification;
import com.laxmi.lifcvisitors.activity.guard.Gaurdregistration;

import java.util.Objects;


public class EmployeeSignup extends AppCompatActivity {
    TextView tv_emp_getotp, tv_login;
    EditText ev_emp_mob_no, emp_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_signup);
        tv_emp_getotp = findViewById(R.id.tv_emp_otp);
        tv_login = findViewById(R.id.tv_login);
        ev_emp_mob_no = findViewById(R.id.ev_emp_mob_no);
        emp_code = findViewById(R.id.ev_empcodes);
        tv_emp_getotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* if (!ev_emp_mob_no.getText().toString().isEmpty() &&
                        ev_emp_mob_no.getText().toString().length() == 10) {
                    Intent intent = new Intent(EmployeeSignup.this, Otpverification.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("mob_no", ev_emp_mob_no.getText().toString());
                    bundle.putString("emp_code", emp_code.getText().toString());
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    Toast.makeText(EmployeeSignup.this, "Enter Mobile No.", Toast.LENGTH_SHORT).show();
                }*/
                if (!validate()) {
                    onUpdateFailed();
                }
                else
                {
                    //Toast.makeText(Gaurdregistration.this, "Enter Mobile No.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EmployeeSignup.this, Otpverification.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("mob_no", ev_emp_mob_no.getText().toString());
                    bundle.putString("emp_code", emp_code.getText().toString());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }

            }
        });

        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmployeeSignup.this, EmployeeLogin.class);
                startActivity(intent);
            }
        });
        TextView tv = (TextView) this.findViewById(R.id.mywidget);
        tv.setSelected(true);
    }
    private void onUpdateFailed() {
        Toast.makeText(EmployeeSignup.this, "Please Give Complete Mobile Number & Employeecode", Toast.LENGTH_SHORT).show();
    }
    private boolean validate() {
        boolean valid = true;
        String empCode = Objects.requireNonNull(emp_code.getText().toString());
        String mobileNo = Objects.requireNonNull(ev_emp_mob_no.getText()).toString();
        if (mobileNo.isEmpty() | mobileNo.length()!=10) {
            ev_emp_mob_no.setError("Enter Valid Mobile Number ");
            requestFocus(ev_emp_mob_no);
            valid = false;
        }
        else {
            ev_emp_mob_no.setError(null);
        }
        if (empCode.isEmpty() | empCode.length()!=4) {
            emp_code.setError("Enter Valid Employee Code");
            requestFocus(emp_code);
            valid = false;
        }
        else {
            emp_code.setError(null);
        }
        return valid;
    }
    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }


}

