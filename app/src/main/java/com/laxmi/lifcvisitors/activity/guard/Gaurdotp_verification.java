package com.laxmi.lifcvisitors.activity.guard;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.laxmi.lifcvisitors.R;

public class Gaurdotp_verification extends AppCompatActivity {
    Intent intent;
    TextView tv_getotp;

    String mob_no,emp_code;
    EditText editTextotp1,editTextotp2,editTextotp3,editTextotp4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaurdotp_verification);
        editTextotp1 = findViewById(R.id.edittext_otp1);
        editTextotp2 = findViewById(R.id.edittext_otp2);
        editTextotp3 = findViewById(R.id.edittext_otp3);
        editTextotp4 = findViewById(R.id.edittext_otp4);
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            mob_no = bundle.getString("mob_no");
            emp_code = bundle.getString("emp_code");
        }
editTextotp1.addTextChangedListener(new TextWatcher() {
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        // TODO Auto-generated method stub
        if (editTextotp1.getText().toString().length() == 1)     //size as per your requirement
        {
            editTextotp2.requestFocus();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start,
                                  int count, int after) {
        // TODO Auto-generated method stub

    }
    @Override
    public void afterTextChanged(Editable editable) {

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
                    tv_getotp.requestFocus();
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
        tv_getotp = findViewById(R.id.tv_gaurd_get_otp);
        tv_getotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Gaurdotp_verification.this, Guard_createpswd.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("mob_no", mob_no);
                    bundle.putString("emp_code", emp_code);
                    intent.putExtras(bundle);
                    startActivity(intent);
            }
        });

        TextView  tv = (TextView) this.findViewById(R.id.mywidget);
        tv.setSelected(true);
    }
}