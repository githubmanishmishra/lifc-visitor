package com.laxmi.lifcvisitors.activity.guard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.laxmi.lifcvisitors.R;

public class Gaurdforgetotp extends AppCompatActivity {
Intent intent;
TextView tvgforgetotp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaurdforgetotp);
        tvgforgetotp = findViewById(R.id.tv_gaurd_verify);
        tvgforgetotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Gaurdforgetotp.this, Guard_createpswd.class);
                startActivity(intent);
            }
        });
        TextView  tv = (TextView) this.findViewById(R.id.mywidget);
        tv.setSelected(true);
    }
}