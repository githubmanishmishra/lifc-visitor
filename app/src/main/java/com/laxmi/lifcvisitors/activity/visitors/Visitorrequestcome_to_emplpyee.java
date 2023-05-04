package com.laxmi.lifcvisitors.activity.visitors;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.laxmi.lifcvisitors.R;
import com.laxmi.lifcvisitors.activity.employee.EmployeeDashboard;
import com.laxmi.lifcvisitors.model.MSG;
import com.laxmi.lifcvisitors.retrofitservices.APIService;
import com.laxmi.lifcvisitors.savedata.PrefConfig;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Visitorrequestcome_to_emplpyee extends AppCompatActivity {
    Spinner Floor;
    Spinner spinner_Conference;
    Button btn_approve, btn_disapprove;
    ArrayList<String> arrFlor = new ArrayList<>();
    ArrayList<String> arrConference = new ArrayList<>();
    boolean isVisible = false;
    PrefConfig prefConfig;

    EditText ev_disapprove;

    TextView tv_visitor_name, tv_visitor_name2, tv_visitor_name3, tv_visitor_mobile, tv_purpose_of_meeting;
    //ImageView view_photo;
/*    private ScaleGestureDetector mScaleGestureDetector;
    private float mScaleFactor = 1.0f;*/
    private ImageView view_photo;

    int visitorId;
     String       VisitorName, VisitorOne, VisitorTwo, VisitorThree, Purpose, UserImage, MobileNo,Status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitorrequestcome_to_emplpyee);

        ImageView iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        prefConfig = new PrefConfig(this);

        btn_approve = findViewById(R.id.btn_approve);
        btn_disapprove = findViewById(R.id.btn_disapprove);
        ev_disapprove = findViewById(R.id.ev_disapprove);

        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            visitorId = mBundle.getInt("visitorId");
            VisitorName = mBundle.getString("VisitorName");
            VisitorOne = mBundle.getString("VisitorOne");
            VisitorTwo = mBundle.getString("VisitorTwo");
            VisitorThree = mBundle.getString("VisitorThree");
            Purpose = mBundle.getString("Purpose");
            MobileNo = mBundle.getString("MobileNo");
            UserImage = mBundle.getString("UserImage");
            Status = mBundle.getString("Status");


        }

       /* if(Status.equalsIgnoreCase("Pending")){
            btn_approve.setVisibility(View.VISIBLE);
            btn_disapprove.setVisibility(View.VISIBLE);
        }else {
            btn_approve.setVisibility(View.GONE);
            btn_disapprove.setVisibility(View.GONE);
        }*/

        tv_visitor_name = findViewById(R.id.tv_visitor_name);
        tv_visitor_name2 = findViewById(R.id.tv_visitor_name2);
        tv_visitor_name3 = findViewById(R.id.tv_visitor_name3);
        tv_visitor_mobile = findViewById(R.id.tv_visitor_mobile);
        tv_purpose_of_meeting = findViewById(R.id.tv_purpose_of_meeting);
        tv_visitor_name.setText(VisitorName);
        tv_visitor_name2.setText(VisitorOne);
        tv_visitor_name3.setText(VisitorTwo);
        tv_visitor_mobile.setText(MobileNo);
        tv_purpose_of_meeting.setText(Purpose);
        view_photo = findViewById(R.id.view_photo);

        //mScaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());

        Glide
                .with(Visitorrequestcome_to_emplpyee.this)
                .load(UserImage)
                .centerCrop()
                .placeholder(R.drawable.ic_alert_red)
                .into(view_photo);
        Floor = findViewById(R.id.spinner_Floor);
        spinner_Conference = findViewById(R.id.spinner_conference);
        arrFlor.add("Basement");
        arrFlor.add("First Floor");
        arrFlor.add("Second Floor");
        arrFlor.add("Third Floor");
        arrFlor.add("Third Floor New Site1");
        arrFlor.add("Fourth Floor");
        arrFlor.add("Roshan Tower Office Unit-3");
        ArrayAdapter<String> springAdapter_Floor = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, arrFlor);
        Floor.setAdapter(springAdapter_Floor);
        arrConference.add("Albert Hall");
        arrConference.add("Jai Garh");
        arrConference.add("City Place");
        arrConference.add("Red Fort");
        arrConference.add("Nahar Garh");
        arrConference.add("Albert Hall");
        arrConference.add("Amer Fort");
        arrConference.add("Hawamahal");
        arrConference.add("Cabin");
        ArrayAdapter<String> springAdapter_Conference = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, arrConference);
        spinner_Conference.setAdapter(springAdapter_Conference);
        btn_disapprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btn_disapprove) {
                    if (!isVisible) {
                        ev_disapprove.setVisibility(View.VISIBLE);
                        isVisible = true;
                        if(ev_disapprove.getText().toString().equalsIgnoreCase("")){
                            Toast.makeText(Visitorrequestcome_to_emplpyee.this, "Please add disapprove reason", Toast.LENGTH_SHORT).show();
                        }else {
                            getVisitorDisApproval();
                        }
                    } else {
                        ev_disapprove.setVisibility(View.GONE);
                        isVisible = false;
                    }
                }
            }
        });
        TextView tv = (TextView) this.findViewById(R.id.mywidget);
        tv.setSelected(true);

        btn_approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 getVisitorApproval();
            }

        });
    }
/*
    public boolean onTouchEvent(MotionEvent motionEvent) {
        mScaleGestureDetector.onTouchEvent(motionEvent);
        return true;
    }
*/
/*
    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector){
            mScaleFactor *= scaleGestureDetector.getScaleFactor();
            mScaleFactor = Math.max(0.1f,
                    Math.min(mScaleFactor, 10.0f));
            view_photo.setScaleX(mScaleFactor);
            view_photo.setScaleY(mScaleFactor);
            return true;
        }
    }
*/

    private void getVisitorApproval() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(
                        chain -> {
                            okhttp3.Request original = chain.request();
                            // Request customization: add request headers
                            okhttp3.Request.Builder requestBuilder = original.newBuilder()
                                    .addHeader("Authorization", "Bearer " + prefConfig.readToken())
                                    .method(original.method(), original.body());
                            okhttp3.Request request = requestBuilder.build();
                            return chain.proceed(request);
                        })
                .addInterceptor(interceptor).connectTimeout(60, TimeUnit.SECONDS).
                readTimeout(60, TimeUnit.SECONDS).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://lifc.shailsoft.com/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
        APIService service = retrofit.create(APIService.class);

        Call<MSG> call = service.getVisitorStatusUpdate("Bearer " + prefConfig.readToken(), ""+visitorId, "Approve",
                spinner_Conference.getSelectedItem().toString()+", "+Floor.getSelectedItem().toString(), "");
        call.enqueue(new Callback<MSG>() {
            @Override
            public void onResponse(@NonNull Call<MSG> call, @NonNull retrofit2.Response<MSG> response) {

                if (response.body() != null) {
                    if (response.body().getMessage().equalsIgnoreCase("Vistor Status Update Successfully")) {

                        Toast.makeText(Visitorrequestcome_to_emplpyee.this, "Updated", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Visitorrequestcome_to_emplpyee.this, EmployeeDashboard.class));
                        finish();

                    }
                } else {
                    Toast.makeText(Visitorrequestcome_to_emplpyee.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(@NonNull Call<MSG> call, @NonNull Throwable t) {

                Log.d("Error", t.getMessage());
            }
        });
    }

    private void getVisitorDisApproval() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(
                        chain -> {
                            okhttp3.Request original = chain.request();
                            // Request customization: add request headers
                            okhttp3.Request.Builder requestBuilder = original.newBuilder()
                                    .addHeader("Authorization", "Bearer " + prefConfig.readToken())
                                    .method(original.method(), original.body());
                            okhttp3.Request request = requestBuilder.build();
                            return chain.proceed(request);
                        })
                .addInterceptor(interceptor).connectTimeout(60, TimeUnit.SECONDS).
                readTimeout(60, TimeUnit.SECONDS).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://lifc.shailsoft.com/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
        APIService service = retrofit.create(APIService.class);

        Call<MSG> call = service.getVisitorStatusUpdate("Bearer " + prefConfig.readToken(), ""+visitorId, "Disapprove",
                spinner_Conference.getSelectedItem().toString()+", "+Floor.getSelectedItem().toString(), ev_disapprove.getText().toString());
        call.enqueue(new Callback<MSG>() {
            @Override
            public void onResponse(@NonNull Call<MSG> call, @NonNull retrofit2.Response<MSG> response) {

                if (response.body() != null) {
                    if (response.body().getMessage().equalsIgnoreCase("Vistor Status Update Successfully")) {

                        Toast.makeText(Visitorrequestcome_to_emplpyee.this, "Updated", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Visitorrequestcome_to_emplpyee.this, EmployeeDashboard.class));
                        finish();

                    }
                } else {
                    Toast.makeText(Visitorrequestcome_to_emplpyee.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(@NonNull Call<MSG> call, @NonNull Throwable t) {

                Log.d("Error", t.getMessage());
            }
        });
    }

}