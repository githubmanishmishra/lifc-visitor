package com.laxmi.lifcvisitors.activity.guard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.laxmi.lifcvisitors.R;

public class Gaurd_mobileno_forget extends AppCompatActivity {
    TextView confirmpsw;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaurd_mobileno_forget);
        confirmpsw = findViewById(R.id.tv_confirmpsw);
        confirmpsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                intent = new Intent(Gaurd_mobileno_forget.this,Gaurdforgetotp.class);
                startActivity(intent);
            }
        });
        TextView  tv = (TextView) this.findViewById(R.id.mywidget);
        tv.setSelected(true);
    }
}