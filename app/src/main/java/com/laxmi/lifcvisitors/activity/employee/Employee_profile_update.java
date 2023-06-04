package com.laxmi.lifcvisitors.activity.employee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.laxmi.lifcvisitors.ImageFilePath;
import com.laxmi.lifcvisitors.R;
import com.laxmi.lifcvisitors.activity.guard.GuardDashboard;
import com.laxmi.lifcvisitors.activity.guard.GuardProfileActivity;
import com.laxmi.lifcvisitors.activity.visitors.New_visitordetail;
import com.laxmi.lifcvisitors.model.Profile;
import com.laxmi.lifcvisitors.retrofitservices.APIService;
import com.laxmi.lifcvisitors.retrofitservices.ApiClient;
import com.laxmi.lifcvisitors.savedata.PrefConfig;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

public class Employee_profile_update extends AppCompatActivity {

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
        setContentView(R.layout.activity_employee_profile_update);
        ImageView btn_save = findViewById(R.id.btn_save);
        circleImageView = findViewById(R.id.profile_image);
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectImage();

            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
        prefConfig = new PrefConfig(this);

        et_name = findViewById(R.id.ev_enter_names);
        et_email = findViewById(R.id.ev_emails);
        et_mobile = findViewById(R.id.ev_mobile_nos);
        et_role = findViewById(R.id.ev_roles);
        et_department = findViewById(R.id.ev_departments);
        btn_submit = findViewById(R.id.btn_submit);
        getProfile();
        btn_submit.setOnClickListener(view -> {
            if (!validate()) {
                onUpdateFailed();
            } else
            {
                save_image_to_memory(uploading_bitmap);
                //
            }
        });

    }

    private boolean validate() {

        boolean valid = true;
        String name = Objects.requireNonNull(et_name.getText()).toString();
        String email = Objects.requireNonNull(et_email.getText().toString());
        String MobileNo = Objects.requireNonNull(et_mobile.getText()).toString();


        if (name.isEmpty() | name.length() < 3) {
            et_name.setError("Name is Empty");
            requestFocus(et_name);
            valid = false;
        } else {
            et_name.setError(null);
        }

        if (MobileNo.isEmpty() | MobileNo.length() != 10) {
            et_mobile.setError("Please enter mobile no.");
            requestFocus(et_mobile);
            valid = false;
        } else {
            et_mobile.setError(null);
        }

        if (email.isEmpty()) {
            et_email.setError("Email is empty");
            requestFocus(et_email);
            valid = false;
        } else {
            et_email.setError(null);
        }
        return valid;

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
                } else {
                    Toast.makeText(Employee_profile_update.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(@NonNull Call<Profile> call, @NonNull Throwable t) {

                Log.d("Error", t.getMessage());
            }
        });
    }

    private void getProfileUpdate(File file) {

        String name = Objects.requireNonNull(et_name.getText()).toString();
        String email = Objects.requireNonNull(et_email.getText().toString());
        String MobileNo = Objects.requireNonNull(et_mobile.getText()).toString();

        RequestBody reqFile1 = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part image1 = MultipartBody.Part.createFormData("image", file.getName(), reqFile1);


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
        Call<ResponseBody> call = service.getProfileUpdate("Bearer " + prefConfig.readToken(),
                MobileNo, name, image1, email);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull retrofit2.Response<ResponseBody> response) {

                if (response.body() != null) {
                    if (response.body().toString().equalsIgnoreCase("Profile Updated Successfully")) {

                        Toast.makeText(Employee_profile_update.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } else {
                    Toast.makeText(Employee_profile_update.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {

                Log.d("Error", t.getMessage());
            }
        });
    }
    //////Image Work//////
    private void checkPermissions(String[] permissionss) {
        try {
            int result;
            List<String> listPermissionsNeeded = new ArrayList<>();
            for (String p : permissionss) {
                result = ContextCompat.checkSelfPermission(this, p);
                if (result != PackageManager.PERMISSION_GRANTED) {
                    listPermissionsNeeded.add(p);
                }
            }
            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 100);
            }

        }
        catch (Exception e) {

        }
    }

    private void selectImage() {
        final CharSequence[] options = {"Take Photo", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, (dialog, item) -> {
            if (options[item].equals("Take Photo")) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else if (options[item].equals("Cancel")) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public void save_image_to_memory(Bitmap bm1) {
        try {
            //---------------
            ContextWrapper cw = new ContextWrapper(Employee_profile_update.this);
            // path to /data/data/yourapp/app_data/imageDir
            File directory = cw.getDir("imageDir", MODE_PRIVATE);
            // Create imageDir
            File mypath = new File(directory, "12.PNG");

            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(mypath);
                // Use the compress method on the BitMap object to write image to the OutputStream
                bm1.compress(Bitmap.CompressFormat.PNG, 100, fos);
            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                try {
                    assert fos != null;
                    fos.close();
                    fos.flush();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //-------------------
            File file1 = new File(directory.getAbsolutePath(), "12.PNG");
            getProfileUpdate(file1);
        }
        catch (Exception e) {
            // progress.cancel();
        }

    }

    private void onUpdateFailed() {
        Toast.makeText(Employee_profile_update.this, "Creating account failed", Toast.LENGTH_LONG).show();

        //  btnCreateAccount.setEnabled(true);
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {

            if (requestCode == 1889) {

                try {
                    Bitmap photo = (Bitmap) data.getExtras().get("data");
                    circleImageView.setImageBitmap(photo);
                    uploading_bitmap = photo;

                    //Remove Image
                    circleImageView.setBackgroundResource(android.R.color.transparent);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if (requestCode == 2) {
                realPath = ImageFilePath.getPath(this, data.getData());
                Uri selectedImage = data.getData();
                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                circleImageView.setImageBitmap(thumbnail);
                uploading_bitmap = thumbnail;
            }
        }
    }

}