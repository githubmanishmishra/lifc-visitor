package com.laxmi.lifcvisitors.activity.guard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.messaging.FirebaseMessaging;
import com.laxmi.lifcvisitors.R;

public class Gaurdregistration extends AppCompatActivity {
    TextView tv_gaurd_getotp,tv_login;
    EditText ev_guard_mob_no,emp_code;
    String  token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaurdregistration);
        // Key token
        //Token Generate
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {

            if (!task.isSuccessful()) {
                Toast.makeText(getApplicationContext(), "Token Not Generated", Toast.LENGTH_SHORT).show();
            }
            token = task.getResult();
            Log.e("Toooooooo", "" + token);

            // storeToken(token);


        });

        tv_gaurd_getotp = findViewById(R.id.tv_gaurdgetotp);
        tv_login = findViewById(R.id.tv_login);
        ev_guard_mob_no = findViewById(R.id.ev_guard_mob_no);
        emp_code = findViewById(R.id.ev_empcodes);
        tv_gaurd_getotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(!ev_guard_mob_no.getText().toString().isEmpty() &&
                        ev_guard_mob_no.getText().toString().length() ==10){
                    Intent intent = new Intent(Gaurdregistration.this,Gaurdotp_verification.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("mob_no", ev_guard_mob_no.getText().toString());
                    bundle.putString("emp_code", emp_code.getText().toString());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(Gaurdregistration.this, "Enter Mobile No.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Gaurdregistration.this,GaurdLogin.class);
                startActivity(intent);
            }
        });
        TextView  tv = (TextView) this.findViewById(R.id.mywidget);
        tv.setSelected(true);
    }
}