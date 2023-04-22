package com.laxmi.lifcvisitors.activity.guard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.laxmi.lifcvisitors.R;

import java.util.Objects;

public class Gaurd_mobileno_forget extends AppCompatActivity {
    TextView confirmpsw;
    Intent intent;
    TextInputEditText textInputMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaurd_mobileno_forget);
         ImageView iv_back =findViewById(R.id.iv_back);
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

                if(Objects.requireNonNull(textInputMobile.getText()).toString().isEmpty() &&
                        textInputMobile.getText().toString().length()!=10){
                    Toast.makeText(Gaurd_mobileno_forget.this, "Enter 10 digit mobile number", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(Gaurd_mobileno_forget.this, GuardForgetOtpVerification.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("mob_no", textInputMobile.getText().toString());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }

            }
        });
        TextView tv = (TextView) this.findViewById(R.id.mywidget);
        tv.setSelected(true);
    }
}