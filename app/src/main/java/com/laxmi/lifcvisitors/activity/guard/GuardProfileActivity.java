package com.laxmi.lifcvisitors.activity.guard;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.laxmi.lifcvisitors.ImageFilePath;
import com.laxmi.lifcvisitors.R;
import com.laxmi.lifcvisitors.activity.employee.Employee_profile_update;
import com.laxmi.lifcvisitors.model.Profile;
import com.laxmi.lifcvisitors.retrofitservices.APIService;
import com.laxmi.lifcvisitors.retrofitservices.ApiClient;
import com.laxmi.lifcvisitors.savedata.PrefConfig;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Guard;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GuardProfileActivity extends AppCompatActivity {

    EditText et_name, et_email, et_mobile, et_role, et_department;
    Button btn_submit;
    PrefConfig prefConfig;

    CircleImageView circleImageView;
    Bitmap uploading_bitmap = null;
    private static final int CAMERA_REQUEST = 1889;
    String realPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardprofile);
        ImageView btn_save = findViewById(R.id.btn_save);
        circleImageView = findViewById(R.id.profile_image);
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //// selectImage();

            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        prefConfig = new PrefConfig(this);
        et_name = findViewById(R.id.ev_enter_name);
        et_email = findViewById(R.id.ev_email);
        et_mobile = findViewById(R.id.ev_mobile_no);
        et_role = findViewById(R.id.ev_role);
        et_department = findViewById(R.id.ev_department);
        btn_submit = findViewById(R.id.btn_submit);
        getProfile();

        btn_submit.setOnClickListener(view -> {

        });


    }

    private void getProfile() {
        APIService service = ApiClient.getClient().create(APIService.class);
        Call<Profile> call = service.getProfile("Bearer " + prefConfig.readToken());
        call.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(@NonNull Call<Profile> call, @NonNull retrofit2.Response<Profile> response) {

                if (response.body() != null) {
                    if (response.body().getMessage().equalsIgnoreCase("Profile Fetched")) {
                        Log.d("Profile", "" + response.body().getData());
                        Profile.Data dataList = response.body().getData();
                        et_mobile.setText(dataList.getMobileNumber());
                        et_role.setText(dataList.getRole());
                        et_name.setText(dataList.getName());
                        et_email.setText(dataList.getEmail());
                        et_department.setText(dataList.getDepartment());

                    }
                } else
                {
                    Toast.makeText(GuardProfileActivity.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(@NonNull Call<Profile> call, @NonNull Throwable t) {

                Log.d("Error", t.getMessage());
            }
        });
    }

}