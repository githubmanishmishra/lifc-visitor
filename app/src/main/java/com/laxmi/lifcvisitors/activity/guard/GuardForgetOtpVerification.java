package com.laxmi.lifcvisitors.activity.guard;

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
import com.laxmi.lifcvisitors.activity.employee.Employeeotpverification;
import com.laxmi.lifcvisitors.activity.employee.Forgetcreatepswemployee;

public class GuardForgetOtpVerification extends AppCompatActivity {
Intent intent;
TextView tvgforgetotp;
EditText editText1,editText2,editText3,editText4;
String mob_no,otpValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaurdforgetotp);
       ImageView iv_back = findViewById(R.id.iv_back);
       editText1 = findViewById(R.id.edit_otp1);
       editText2 = findViewById(R.id.edit_otp2);
       editText3 = findViewById(R.id.edit_otp3);
       editText4 = findViewById(R.id.edit_otp4);

        TextView  tv = (TextView) this.findViewById(R.id.mywidget);
        tv.setSelected(true);
       iv_back.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               finish();
           }
       });

        editText1.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (editText1.getText().toString().length() == 1)     //size as per your requirement
                {
                    editText2.requestFocus();
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
        editText2.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (editText2.getText().toString().length() == 1)     //size as per your requirement
                {
                    editText3.requestFocus();
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
        editText3.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (editText3.getText().toString().length() == 1)     //size as per your requirement
                {
                    editText4.requestFocus();
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
        editText4.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (editText1.getText().toString().length() == 1)     //size as per your requirement
                {
                    tvgforgetotp.requestFocus();
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


        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            mob_no = bundle.getString("mob_no");
            otpValue = bundle.getString("otpValue");
        }

        tvgforgetotp = findViewById(R.id.tv_gaurd_verify);
        tvgforgetotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  intent = new Intent(GuardForgetOtpVerification.this, Guard_createpswd.class);
                startActivity(intent);*//*
                Intent intent = new Intent(GuardForgetOtpVerification.this, GuardForgotPassword.class);
                Bundle bundle = new Bundle();
                bundle.putString("mob_no", mob_no);
                intent.putExtras(bundle);
                startActivity(intent);*/
                if (!editText1.getText().toString().isEmpty() &&
                        !editText2.getText().toString().isEmpty() &&
                        !editText3.getText().toString().isEmpty() &&
                        ! editText4.getText().toString().isEmpty())
                {
                    if(otpValue.equalsIgnoreCase(editText1.getText().toString()+
                            editText2.getText().toString()+editText3.getText().toString()
                            +editText4.getText().toString())){
                        Intent intent = new Intent(GuardForgetOtpVerification.this, GuardForgotPassword.class);
                        Bundle bundle1 = new Bundle();
                        bundle1.putString("mob_no", mob_no);
                        //          bundle1.putString("emp_code", emp_code);
                        intent.putExtras(bundle1);
                        startActivity(intent);

                    }

            }
                else
                {
                    Toast.makeText(GuardForgetOtpVerification.this, "Enter Otp sent on your mobile", Toast.LENGTH_SHORT).show();
                }

            }});

}
}