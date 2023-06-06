package com.laxmi.lifcvisitors.activity.employee;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.laxmi.lifcvisitors.Contactus;
import com.laxmi.lifcvisitors.ImageFilePath;
import com.laxmi.lifcvisitors.R;
import com.laxmi.lifcvisitors.activity.guard.GaurdLogin;
import com.laxmi.lifcvisitors.activity.guard.GuardDashboard;
import com.laxmi.lifcvisitors.activity.visitors.Visitorrequestcome_to_emplpyee;
import com.laxmi.lifcvisitors.fragments.DashboardFragment;
import com.laxmi.lifcvisitors.model.Profile;
import com.laxmi.lifcvisitors.retrofitservices.APIService;
import com.laxmi.lifcvisitors.savedata.PrefConfig;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

public class EmployeeDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    PrefConfig prefConfig;
    Bitmap uploading_bitmap = null;
    private static final int CAMERA_REQUEST = 1889;
    String realPath;
    AlertDialog.Builder alertDialogBuilder;
    CircleImageView profile_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_dashboard);
        Toolbar toolbar = findViewById(R.id.toolbar);
        prefConfig = new PrefConfig(this);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.bringToFront();
        View headerView = navigationView.inflateHeaderView(R.layout.nav_header_main);
        TextView tv_emp_name = headerView.findViewById(R.id.tv_emp_name);
        TextView tv_emp_email = headerView.findViewById(R.id.tv_emp_email);
        profile_img = headerView.findViewById(R.id.profile_image);
        profile_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();

                //  getalertcamera();
       /* ImagePicker.with(EmployeeDashboard.this)
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start();*/

            }
        });
        tv_emp_name.setText(prefConfig.readName() + "");
        tv_emp_email.setText(prefConfig.readEmail() + "");
//        profile_img.setImageURI(Uri.parse(prefConfig.readProfile_image()+""));
        Glide
                .with(EmployeeDashboard.this)
                .load(prefConfig.readProfile_image())
                .centerCrop()
                .placeholder(R.drawable.ic_alert_red)
                .into(profile_img);

        alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Logout");
        alertDialogBuilder.setIcon(R.drawable.logout);
        alertDialogBuilder.setMessage("Are you sure want to logout?");
        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(EmployeeDashboard.this, EmployeeLogin.class));
                Toast.makeText(EmployeeDashboard.this, "You successfully logout", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getApplicationContext();
            }
        });
        FragmentManager fragmentManager = getSupportFragmentManager();
        DashboardFragment fragment = new DashboardFragment();
        fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit();

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            // super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        //  Fragment fragment = null;
        //   FragmentManager fragmentManager = getSupportFragmentManager();
        if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(EmployeeDashboard.this, Employee_profile_update.class);
            startActivity(intent);
        } else if (id == R.id.nav_Contactus) {
            Intent intents = new Intent(EmployeeDashboard.this, Contactus.class);
            startActivity(intents);
        } else if (id == R.id.nav_logout) {
            alertDialogBuilder.show();
            prefConfig.writeLoginStatus(false);


            //startActivity(new Intent(EmployeeDashboard.this, EmployeeLogin.class));
            //    finishAffinity();

        }
        //  fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

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
            ContextWrapper cw = new ContextWrapper(EmployeeDashboard.this);
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
            } finally {
                try {
                    assert fos != null;
                    fos.close();
                    fos.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //-------------------
            File file1 = new File(directory.getAbsolutePath(), "12.PNG");
            getProfileUpdate(file1);
        } catch (Exception e) {
            // progress.cancel();
        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {

            if (requestCode == 1889) {


                try {
                    Bitmap photo = (Bitmap) data.getExtras().get("data");

                    save_image_to_memory(photo);

                    profile_img.setImageBitmap(photo);
                    uploading_bitmap = photo;


                    //Remove Image
                    profile_img.setBackgroundResource(android.R.color.transparent);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == 2) {
                realPath = ImageFilePath.getPath(this, data.getData());
                Uri selectedImage = data.getData();
                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                profile_img.setImageBitmap(thumbnail);
                uploading_bitmap = thumbnail;
            }
        }
    }


    private void getProfileUpdate(File file) {

//        String name = Objects.requireNonNull(et_name.getText()).toString();
//        String email = Objects.requireNonNull(et_email.getText().toString());
//        String MobileNo = Objects.requireNonNull(et_mobile.getText()).toString();

        RequestBody reqFile1 = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part image1 = MultipartBody.Part.createFormData("image", file.getName(), reqFile1);

        Log.d("ksjdgn", "" + image1);


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
        Call<ResponseBody> call = service.getProfileUpdate1("Bearer " + prefConfig.readToken(),
                "7503196856", prefConfig.readName(), image1, prefConfig.readEmail());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull retrofit2.Response<ResponseBody> response) {

                //   if (response.body() != null) {
                if (response.message().equalsIgnoreCase("Profile Updated Successfully")) {

                    Toast.makeText(EmployeeDashboard.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                    finish();

                }
                //   }
                else {
                    Toast.makeText(EmployeeDashboard.this, "Manishhhhh" + response.message(), Toast.LENGTH_SHORT).show();
                    Log.d("jhjjjgjgjg", "Manishhhhh" + response.message());
                }

            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {

                Log.d("Error", t.getMessage());
            }
        });
    }


}