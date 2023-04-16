package com.laxmi.lifcvisitors.activity.visitors;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.laxmi.lifcvisitors.Employee_Send_Request_toGaurd;
import com.laxmi.lifcvisitors.R;
import com.laxmi.lifcvisitors.model.Branches;
import com.laxmi.lifcvisitors.model.Departments;
import com.laxmi.lifcvisitors.retrofitservices.APIService;
import com.laxmi.lifcvisitors.retrofitservices.ApiClient;
import com.laxmi.lifcvisitors.savedata.PrefConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

public class New_visitordetail extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button btn_uploadvisitor_photo;
    ImageView visitorPhoto;
    AppCompatButton fbDialog;

    TextView rv_log;
    LinearLayout linearLayout;
    public boolean checkHide = false;


    Spinner spinner, spinner_department, spinner_branches;
    TextView tv_spinner_state;
    List<String> listSpinner = new ArrayList<>();
    List<String> listDepartment = new ArrayList<>();
    List<String> listBranches = new ArrayList<>();
    AppCompatButton btnSubmit;
    PrefConfig prefConfig;

    //Image Work
    private static final int CAMERA_REQUEST = 1888;
    Bitmap uploading_bitmap = null;

    String realPath;
    private static final int PERMISSION_REQUEST_CODE = 1;


    String[] permissions_location = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_visitordetail);

        prefConfig = new PrefConfig(this);

        Log.d("token>>>>>>>M", prefConfig.readToken());

        //Permission
        allowPermission();


        btn_uploadvisitor_photo = findViewById(R.id.btn_uploadphoto);

        fbDialog = findViewById(R.id.floating_btn);
        visitorPhoto = findViewById(R.id.visitor_photo);
        linearLayout = findViewById(R.id.editTextContainer);
        EditText pin_code = findViewById(R.id.pin_code);
        tv_spinner_state = findViewById(R.id.tv_spinner_state);
        spinner = findViewById(R.id.spinner_city);
        spinner_department = findViewById(R.id.spinner_department);
        spinner_branches = findViewById(R.id.spinner_branches);
        btnSubmit = findViewById(R.id.btn_submiit);
        spinner.setOnItemSelectedListener(this);

        btnSubmit.setOnClickListener(view -> {
            if (pin_code.getText().toString().isEmpty() &&
                    pin_code.getText().toString().length() != 6) {
                Toast.makeText(New_visitordetail.this, "Enter Six Digit Pin code", Toast.LENGTH_SHORT).show();
                if (tv_spinner_state.getText().toString().isEmpty()) {
                    Toast.makeText(New_visitordetail.this, "Enter Six Digit Pin code", Toast.LENGTH_SHORT).show();
                }
            } else {
                getState(pin_code.getText().toString());
            }
        });

        fbDialog.setOnClickListener(view -> {

            if (checkHide) {
                checkHide = false;
                linearLayout.setVisibility(View.GONE);

            } else {
                checkHide = true;
                linearLayout.setVisibility(View.VISIBLE);
            }

        });

        btn_uploadvisitor_photo.setOnClickListener(view -> {
            selectImage();
        });

        rv_log = findViewById(R.id.sendrequest);
        rv_log.setOnClickListener(view -> {
            Intent intent = new Intent(New_visitordetail.this, Employee_Send_Request_toGaurd.class);
            startActivity(intent);
        });
        TextView tv = this.findViewById(R.id.mywidget);
        tv.setSelected(true);

        getDepartments();
        getBranches();

    }

    private void allowPermission() {
        //Location Permission
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            // do you work now

                        }
                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // permission is denied permenantly, navigate user to app settings
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {

                    }

                })
                .onSameThread()
                .check();

        if (Build.VERSION.SDK_INT >= 23) {
            checkPermissions(permissions_location);
        } else {

            Toast.makeText(New_visitordetail.this, "Please allow storage location", Toast.LENGTH_SHORT).show();
        }
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

        } catch (Exception e) {

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


            } /*else if (options[item].equals("Choose from Gallery")) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 2);
            } */ else if (options[item].equals("Cancel")) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public void save_image_to_memory(Bitmap bm1, String data) {
        try {
            //---------------
            ContextWrapper cw = new ContextWrapper(New_visitordetail.this);
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
          //  performDataUpdation(file1, data);
        } catch (Exception e) {
            // progress.cancel();
        }

    }

    private void performDataUpdation(File file1, String token, String data) {
       /* pDialog = new ProgressDialog(this);
        pDialog.setIndeterminate(false);
        pDialog.setMessage("Creating Account...");
        pDialog.setCancelable(false);

        showpDialog();*/


        RequestBody reqFile1 = RequestBody.create(MediaType.parse("image/*"), file1);
        MultipartBody.Part image1 = MultipartBody.Part.createFormData("image", file1.getName(), reqFile1);


       /* Call<SignUpModel> req = apiInterface.signUp(image1);


        req.enqueue(new Callback<SignUpModel>() {
            @Override
            public void onResponse(Call<SignUpModel> call, retrofit2.Response<SignUpModel> response) {
                pDialog.dismiss();
                try {
                    if (response.isSuccessful()) {
                        //  JSONObject myListsAll = new JSONObject(response.body().string());
                        assert response.body() != null;
                        String string_message = response.body().getMessage();
                        String name = response.body().getName();
                        String email = response.body().getEmail();
                        int login_id = response.body().getLoginId();
                        int login_status = response.body().getLoginStatus();
                        //   String servicess = myListsAll.getString("service");
                        String pic = response.body().getPic();

                        if (string_message.equalsIgnoreCase("User registered successfully")) {

                            editor.putString("name", firstName + lastName);
                            editor.putString("address", address);
                            editor.putString("pincode", pincode);
                            editor.putString("city", cityValue);
                            editor.putString("state", stateValue);
                            editor.putString("data", data);
                            editor.apply();

                            LoginRegActivity.prefConfig.writeLoginStatus(true);

                            prefConfig.writeName(name, email, String.valueOf(login_id), pic, android_id, data);
                            Toast.makeText(RegistrationActivity.this, "" + string_message, Toast.LENGTH_SHORT).show();

                            //onLoginFormActivityListener.performLogin(name, email, login_id, pic);

                           *//* startActivity(new Intent(RegistrationActivity.this, HomeScreen.class));
                            finish();
*//*
                            Intent intent1 = new Intent(RegistrationActivity.this, HomeScreen.class);
                            startActivity(intent1);
                            finishAffinity();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("fgbfgb", e.getMessage());
                }

            }

            @Override
            public void onFailure(Call<SignUpModel> call, Throwable t) {
                Log.e("ksjfh", call.toString() + "" + t);
                pDialog.dismiss();
                //    call.cancel();
                //   pDialog.cancel();
            }
        });*/
    }


    private void getState(String pinCode) {

        //RequestQueue initialized
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);

        //String Request initialized
        //display the response on screen
        //returnFlights objects
        //Creating the ArrayAdapter instance having the country list
        //Setting the ArrayAdapter data on the Spinner
        String url = "https://api.postalpincode.in/pincode";
        StringRequest mStringRequest = new StringRequest(Request.Method.GET, url + "/" + pinCode, response -> {

            Toast.makeText(getApplicationContext(), "Response :" + response, Toast.LENGTH_LONG).show();//display the response on screen
            Log.i("Manish Mishra", response);

            if (response != null) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject returnFlightChild = null;
                    for (int j = 0; j < jsonArray.length(); j++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(j);
                        JSONArray onwardflights = jsonObject.getJSONArray("PostOffice");
                        for (int i = 0; i < onwardflights.length(); i++) {
                            returnFlightChild = onwardflights.getJSONObject(i);
                            //returnFlights objects
                            Log.d("kjxngksjnkjsdn", returnFlightChild.getString("Name"));
                            Log.d("kjxngksjnkjsdn", returnFlightChild.getString("Circle"));
                            listSpinner.add(returnFlightChild.getString("Name"));
                            tv_spinner_state.setText(returnFlightChild.getString("Circle"));

                        }
                        //Creating the ArrayAdapter instance having the country list  
                        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listSpinner);
                        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Setting the ArrayAdapter data on the Spinner  
                        spinner.setAdapter(aa);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else {
                Toast.makeText(New_visitordetail.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i("Manish", "Error :" + error.toString());
            }
        });

        mRequestQueue.add(mStringRequest);

    }

    private void getDepartments() {
        APIService service = ApiClient.getClient().create(APIService.class);
        Call<Departments> call = service.getDepartments("Bearer " + prefConfig.readToken());
        call.enqueue(new Callback<Departments>() {
            @Override
            public void onResponse(@NonNull Call<Departments> call, @NonNull retrofit2.Response<Departments> response) {

                if (response.body() != null) {
                    if (response.body().getMessage().equalsIgnoreCase("Departments List")) {

                        Log.d("Departmentsssss", "" + response.body().getData());

                        List<Departments.Data> dataList = response.body().getData();

                        for (int i = 0; i < dataList.size(); i++) {

                            Log.d("kjxngksjnkjsdn", dataList.toString());

                            HashSet<String> hashSet = new HashSet<String>();
                            hashSet.addAll(listDepartment);
                            listDepartment.clear();
                            listDepartment.addAll(hashSet);

                            listDepartment.add(dataList.get(i).getDepartmentName());

                        }
                        //Creating the ArrayAdapter instance having the country list
                        ArrayAdapter aa = new ArrayAdapter(New_visitordetail.this, android.R.layout.simple_spinner_item, listDepartment);
                        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Setting the ArrayAdapter data on the Spinner
                        spinner_department.setAdapter(aa);


                    }
                } else {
                    Toast.makeText(New_visitordetail.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(@NonNull Call<Departments> call, @NonNull Throwable t) {

                // pDialog.dismiss();
                Log.d("Error", t.getMessage());
            }
        });
    }

    private void getBranches() {
        APIService service = ApiClient.getClient().create(APIService.class);
        Call<Branches> call = service.getBranches("Bearer " + prefConfig.readToken());
        call.enqueue(new Callback<Branches>() {
            @Override
            public void onResponse(@NonNull Call<Branches> call, @NonNull retrofit2.Response<Branches> response) {

                if (response.body() != null) {
                    if (response.body().getMessage().equalsIgnoreCase("Branches List")) {

                        Log.d("Branchessssss", "" + response.body().getData());

                        List<Branches.Data> dataList = response.body().getData();

                        for (int i = 0; i < dataList.size(); i++) {

                            Log.d("kjxngksjnkjsdn", dataList.toString());

                            HashSet<String> hashSet = new HashSet<String>();
                            hashSet.addAll(listBranches);
                            listBranches.clear();
                            listBranches.addAll(hashSet);

                            listBranches.add(dataList.get(i).getBranchName());

                        }
                        //Creating the ArrayAdapter instance having the country list
                        ArrayAdapter aa = new ArrayAdapter(New_visitordetail.this, android.R.layout.simple_spinner_item, listBranches);
                        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Setting the ArrayAdapter data on the Spinner
                        spinner_branches.setAdapter(aa);


                    }
                } else {
                    Toast.makeText(New_visitordetail.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(@NonNull Call<Branches> call, @NonNull Throwable t) {

                // pDialog.dismiss();
                Log.d("Error", t.getMessage());
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}