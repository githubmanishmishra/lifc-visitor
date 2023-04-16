package com.laxmi.lifcvisitors.activity.guard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.laxmi.lifcvisitors.R;

public class GuardForgetOtpVerification extends AppCompatActivity {
Intent intent;
TextView tvgforgetotp;
String mob_no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaurdforgetotp);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            mob_no = bundle.getString("mob_no");
        }

        tvgforgetotp = findViewById(R.id.tv_gaurd_verify);
        tvgforgetotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  intent = new Intent(GuardForgetOtpVerification.this, Guard_createpswd.class);
                startActivity(intent);*/
                Intent intent = new Intent(GuardForgetOtpVerification.this, GuardForgotPassword.class);
                Bundle bundle = new Bundle();
                bundle.putString("mob_no", mob_no);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        TextView  tv = (TextView) this.findViewById(R.id.mywidget);
        tv.setSelected(true);
    }
}