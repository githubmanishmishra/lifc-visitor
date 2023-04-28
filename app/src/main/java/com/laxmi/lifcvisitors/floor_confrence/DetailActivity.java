package com.laxmi.lifcvisitors.floor_confrence;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.laxmi.lifcvisitors.R;
import com.laxmi.lifcvisitors.activity.officeboy.Office_boyassign;

public class DetailActivity extends AppCompatActivity {

    TextView mIcon;
    TextView mSender;
    TextView mEmailTitle;
    TextView mEmailDetails;
    TextView mEmailTime;
    Button btn_status;
    ImageView mFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mIcon = findViewById(R.id.tvIcon);
        mSender = findViewById(R.id.tvEmailSender);
        mEmailTitle = findViewById(R.id.tvEmailTitle);
        mEmailDetails = findViewById(R.id.tvEmailDetails);
        mEmailTime = findViewById(R.id.tvEmailTime);
        btn_status = findViewById(R.id.btn_status);
        mFavorite = findViewById(R.id.ivFavorite);

        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            mIcon.setText(mBundle.getString("icon"));
            ((GradientDrawable) mIcon.getBackground()).setColor(mBundle.getInt("colorIcon"));
            mSender.setText(mBundle.getString("sender"));
            mEmailTitle.setVisibility(View.INVISIBLE);

            mEmailDetails.setText(mBundle.getString("details"));
            mEmailTime.setText(mBundle.getString("time"));
           String Status = mBundle.getString("Status");
           int visitorId = mBundle.getInt("visitorId");
           String employeeId = mBundle.getString("employeeId");

          /* if(Status.equalsIgnoreCase("Pending") |Status.equalsIgnoreCase("Disapprove") ){
               btn_status.setOnClickListener(null);
               Toast.makeText(DetailActivity.this, "Wait for Approval", Toast.LENGTH_SHORT).show();

           }else {
               btn_status.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {

                       startActivity(new Intent(DetailActivity.this, Office_boyassign.class));

                   }
               });
           }*/

            btn_status.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                   // startActivity(new Intent(DetailActivity.this, Office_boyassign.class));

                    Intent mIntent = new Intent(DetailActivity.this, Office_boyassign.class);
                    mIntent.putExtra("Status", Status);
                    mIntent.putExtra("visitorId", visitorId);
                    mIntent.putExtra("sender", mBundle.getString("sender"));
                    mIntent.putExtra("title", mBundle.getString("title"));
                    startActivity(mIntent);

                }
            });

            btn_status.setText("Assign Office Boy");
        }
        mFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mFavorite.getColorFilter() != null) {
                    mFavorite.clearColorFilter();
                } else {
                    mFavorite.setColorFilter(ContextCompat.getColor(DetailActivity.this,
                            R.color.purple_200));
                }
            }
        });
    }
}
