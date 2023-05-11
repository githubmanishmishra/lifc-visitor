package com.laxmi.lifcvisitors.activity.employee;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.laxmi.lifcvisitors.R;
import com.laxmi.lifcvisitors.activity.guard.GuardForgetOtpVerification;
import com.laxmi.lifcvisitors.activity.guard.GuardForgotPassword;

public class Employeeotpverification extends AppCompatActivity {
    Intent intent;
    TextView getotp;
    EditText editTextotp1, editTextotp2, editTextotp3, editTextotp4;

    String mob_no,otpValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverification_forget);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mob_no = bundle.getString("mob_no");
            otpValue = bundle.getString("otpValue");
        }
        getotp = findViewById(R.id.tv_getotp);
        editTextotp1 = findViewById(R.id.edittext_otp1);
        editTextotp2 = findViewById(R.id.edittext_otp2);
        editTextotp3 = findViewById(R.id.edittext_otp3);
        editTextotp4 = findViewById(R.id.edittext_otp4);
         ImageView iv_back =findViewById(R.id.iv_back);
         iv_back.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 finish();
             }
         });
        editTextotp1.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (editTextotp1.getText().toString().length() == 1)     //size as per your requirement
                {
                    editTextotp2.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });
        editTextotp2.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (editTextotp2.getText().toString().length() == 1)     //size as per your requirement
                {
                    editTextotp3.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });
        editTextotp3.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (editTextotp3.getText().toString().length() == 1)     //size as per your requirement
                {
                    editTextotp4.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });
        editTextotp4.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (editTextotp1.getText().toString().length() == 1)     //size as per your requirement
                {
                    getotp.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });

        getotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
/*                Intent intent = new Intent(Employeeotpverification.this, Forgetcreatepswemployee.class);
                Bundle bundle = new Bundle();
                bundle.putString("mob_no", mob_no);
                intent.putExtras(bundle);
                startActivity(intent);*/
                if (!editTextotp1.getText().toString().isEmpty() &&
                        !editTextotp2.getText().toString().isEmpty() &&
                        !editTextotp3.getText().toString().isEmpty() &&
                        ! editTextotp4.getText().toString().isEmpty())
                {
                    if(otpValue.equalsIgnoreCase(editTextotp1.getText().toString()+
                            editTextotp2.getText().toString()+editTextotp3.getText().toString()
                            +editTextotp4.getText().toString())){

                        Intent intent = new Intent(Employeeotpverification.this, Forgetcreatepswemployee.class);
                        Bundle bundle1 = new Bundle();
                        bundle1.putString("mob_no", mob_no);
              //          bundle1.putString("emp_code", emp_code);
                        intent.putExtras(bundle1);
                        startActivity(intent);

                    }
                }
                else
                {
                    Toast.makeText(Employeeotpverification.this, "Enter Otp sent on your mobile", Toast.LENGTH_SHORT).show();
                }


            }
        });
        TextView   tv = (TextView) this.findViewById(R.id.mywidget);
        tv.setSelected(true);

    }
}