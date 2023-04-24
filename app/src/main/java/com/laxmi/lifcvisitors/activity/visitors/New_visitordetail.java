package com.laxmi.lifcvisitors.activity.visitors;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
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
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.laxmi.lifcvisitors.Employee_Send_Request_toGaurd;
import com.laxmi.lifcvisitors.ImageFilePath;
import com.laxmi.lifcvisitors.R;
import com.laxmi.lifcvisitors.activity.employee.EmployeeDashboard;
import com.laxmi.lifcvisitors.activity.employee.EmployeeLogin;
import com.laxmi.lifcvisitors.languageconvert.BaseActivity;
import com.laxmi.lifcvisitors.model.Branches;
import com.laxmi.lifcvisitors.model.Departments;
import com.laxmi.lifcvisitors.model.EmployeeByDepartment;
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
import java.util.Calendar;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class New_visitordetail extends BaseActivity implements
        View.OnClickListener {

    ImageView btn_uploadvisitor_photo;
    TextInputEditText visitor_name, pin_code, visitor_name1, visitor_name2, visitor_name3, purpose_of_comeing, visitor_mobileno;
    ImageView visitorPhoto;
    AppCompatButton fbDialog;
    TextView rv_log;
    String cityValue;
    LinearLayout linearLayout;
    public boolean checkHide = false;
    Spinner spinner, spinner_department, spinner_branches, spinner_employeedept;
    TextView tv_spinner_state;
    List<String> listSpinner = new ArrayList<>();
    List<String> listBranches = new ArrayList<>();

    Departments.Data departments;
    EmployeeByDepartment.Data empByDepartment;

    List<Departments.Data> dataList = new ArrayList<>();
    List<EmployeeByDepartment.Data> dataListEmp = new ArrayList<>();

    // String departmentsValue;
    String departmentsEmp;
    AppCompatButton btnSubmit, Getotp;
    PrefConfig prefConfig;
    private final Handler handler = new Handler();

    TextView tv_emp_code;
    ImageView iv_back;

    Button btnTimePickerIn, btnTimePickerOut;
    EditText txtTimeIn, txtTimeOut;
    private int mHour, mMinute;
    Bitmap uploading_bitmap = null;
    String empMobileNo;

    private ProgressDialog pDialog;

    //Image Work
    private static final int CAMERA_REQUEST = 1888;
    String realPath;
    private static final int PERMISSION_REQUEST_CODE = 1;


    String[] permissions_location = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
    };
    String visitorName, TxtTimeout, TxtTimeIn, Visitorname1, Visitorname2, Visitorname3, Purposeofcomeing, Visitor_mobile_no;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_visitordetail);
        prefConfig = new PrefConfig(this);
        //Timer Work
        visitor_name = findViewById(R.id.ev_visitor_name);
        visitor_name1 = findViewById(R.id.ev_visitor_one);
        visitor_name2 = findViewById(R.id.ev_visitor_two);
        visitor_name3 = findViewById(R.id.ev_visitor_three);
        purpose_of_comeing = findViewById(R.id.purposeof_meeting);
        visitor_mobileno = findViewById(R.id.visitormobile_no);

        TextView _tv = (TextView) findViewById(R.id.textView1);
        btnTimePickerIn = (Button) findViewById(R.id.btn__in_time);
        btnTimePickerOut = (Button) findViewById(R.id.btn_out_time);
        txtTimeIn = (EditText) findViewById(R.id.in_time);
        txtTimeOut = findViewById(R.id.out_time);
        spinner_employeedept = findViewById(R.id.spinner_employeedept);
        tv_emp_code = findViewById(R.id.tv_emp_code);
        iv_back = findViewById(R.id.iv_back);

        visitorName = visitor_name.getText().toString().trim();
        Visitorname1 = visitor_name1.getText().toString().trim();
        Visitorname2 = visitor_name2.getText().toString().trim();
        Visitorname3 = visitor_name3.getText().toString().trim();
        Visitor_mobile_no = visitor_mobileno.getText().toString().trim();
        TxtTimeout = txtTimeOut.getText().toString().trim();
        TxtTimeIn = txtTimeIn.getText().toString().trim();
        Purposeofcomeing = purpose_of_comeing.getText().toString().trim();


        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnTimePickerIn.setOnClickListener(this);
        btnTimePickerOut.setOnClickListener(this);
        new CountDownTimer(120000, 1000) {

            public void onTick(long millisUntilFinished) {
                _tv.setText("seconds remaining: " + millisUntilFinished / 1000);
                // logic to set the EditText could go here
            }

            public void onFinish() {
                _tv.setText("done!");
            }

        }.start();

        Log.d("token>>>>>>>M", prefConfig.readToken());

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Permission
        allowPermission();
        Getotp = findViewById(R.id.btn_otp);
        Getotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(New_visitordetail.this, "manish", Toast.LENGTH_SHORT).show();
            }
        });
        btn_uploadvisitor_photo = findViewById(R.id.btn_uploadphoto);

        fbDialog = findViewById(R.id.floating_btn);
        visitorPhoto = findViewById(R.id.visitor_photo);
        linearLayout = findViewById(R.id.editTextContainer);
        pin_code = findViewById(R.id.pin_code);
        tv_spinner_state = findViewById(R.id.tv_spinner_state);
        spinner = findViewById(R.id.spinner_city);
        spinner_department = findViewById(R.id.spinner_department);
        spinner_branches = findViewById(R.id.spinner_branches);
        btnSubmit = findViewById(R.id.btn_submiit);

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

           /* if (visitorPhoto.getBackground().getConstantState().equals(getResources().getDrawable(R.drawable.baseline_person_24).getConstantState())) {
                Toast.makeText(New_visitordetail.this, "Please Upload Photo", Toast.LENGTH_LONG).show();
                // profile_pic.setBackgroundResource(android.R.color.transparent);
            } else {
                save_image_to_memory(uploading_bitmap);

            }*/

            selectImage();

        });

        rv_log = findViewById(R.id.sendrequest);
        rv_log.setOnClickListener(view -> {

            save_image_to_memory();

        });
        TextView tv = this.findViewById(R.id.mywidget);
        tv.setSelected(true);
        getDepartments();
        //  getBranches();

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
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
            } else if (options[item].equals("Cancel")) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public void save_image_to_memory() {
        try {
            Bitmap bm1 = null;
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
//                bm1 = ((BitmapDrawable) visitorPhoto.getDrawable()).getBitmap();
//                bm1.compress(Bitmap.CompressFormat.PNG, 100, fos);
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
            registrationApi(file1);
        } catch (Exception e) {
            // progress.cancel();
        }

    }


    private void getState(String pinCode) {

        //RequestQueue initialized
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);

        String url = "https://api.postalpincode.in/pincode";
        StringRequest mStringRequest = new StringRequest(Request.Method.GET, url + "/" + pinCode, response -> {

            Toast.makeText(getApplicationContext(), "Response :" + response, Toast.LENGTH_LONG).show();//display the response on screen
            Log.i("Manish Mishra", response);

            if (response != null) {
                //  pDialog.dismiss();
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
                        aa.notifyDataSetChanged();

                        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                try {
                                    cityValue = onwardflights.get(position).toString();
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else {
                Toast.makeText(New_visitordetail.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();

            }

        }, error -> Log.i("Manish", "Error :" + error.toString()));

        mRequestQueue.add(mStringRequest);

    }

    private void getDepartments() {
        APIService service = ApiClient.getClient().create(APIService.class);
        Call<Departments> call = service.getDepartments("Bearer " + prefConfig.readToken());
        call.enqueue(new Callback<Departments>() {
            @Override
            public void onResponse(@NonNull Call<Departments> call, @NonNull retrofit2.Response<Departments> response) {

                if (response.body() != null) {
                    //  pDialog.dismiss();
                    if (response.body().getMessage().equalsIgnoreCase("Departments List")) {

                        Log.d("Departmentsssss", "" + response.body().getData());

                        dataList = response.body().getData();
                        List<String> listDepartment = new ArrayList<>();
                        for (int i = 0; i < dataList.size(); i++) {

                            Log.d("kjxngksjnkjsdn", dataList.toString());
//                            HashSet<String> hashSet = new HashSet<String>();
//                            hashSet.addAll(listDepartment);
//                            listDepartment.clear();
//                            listDepartment.addAll(hashSet);
                            listDepartment.add(dataList.get(i).getDepartmentName());


                        }
                        //Creating the ArrayAdapter instance having the country list
                        ArrayAdapter aa = new ArrayAdapter(New_visitordetail.this, android.R.layout.simple_spinner_item, listDepartment);
                        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Setting the ArrayAdapter data on the Spinner

                        spinner_department.setAdapter(aa);
                        aa.notifyDataSetChanged();

                    }

                    spinner_department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            try {
                                departments = dataList.get(position);

                                String departmentsValue = departments.getDepartmentName();
                                //  String  departmentsValueCode = departments.getDepartmentCode();

                                //   Log.d("menu111", departmentsValueCode);

                                getdepartmentOfEmployee(departmentsValue);


                            } catch (Exception e) {

                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

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

    private void getdepartmentOfEmployee(String departmentsValue) {
        Log.d("Employe name", departmentsValue);
        APIService service = ApiClient.getClient().create(APIService.class);
        Call<EmployeeByDepartment> call = service.getemployeesbydepartment("Bearer " + prefConfig.readToken(), departmentsValue);
        call.enqueue(new Callback<EmployeeByDepartment>() {
            @Override
            public void onResponse(@NonNull Call<EmployeeByDepartment> call, @NonNull retrofit2.Response<EmployeeByDepartment> response) {

                if (response.body() != null) {
                    //  pDialog.dismiss();
                    if (response.body().getMessage().equalsIgnoreCase("Department Wise Employee Fatched successfully")) {

                        Log.d("DepartmentsssssE", "" + response.body().getData());


                        dataListEmp = response.body().getData();
                        List<String> listDepartmentEmp = new ArrayList<>();
                        for (int i = 0; i < dataListEmp.size(); i++) {

                            Log.d("kjxngksjnkjsdnE", dataListEmp.toString());
//                            HashSet<String> hashSet = new HashSet<String>();
//                            hashSet.addAll(listDepartment);
//                            listDepartment.clear();
//                            listDepartment.addAll(hashSet);
                            listDepartmentEmp.add(dataListEmp.get(i).getName());


                        }
                        //Creating the ArrayAdapter instance having the country list
                        ArrayAdapter aa = new ArrayAdapter(New_visitordetail.this, android.R.layout.simple_spinner_item, listDepartmentEmp);
                        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Setting the ArrayAdapter data on the Spinner

                        spinner_employeedept.setAdapter(aa);
                        aa.notifyDataSetChanged();

                    }

                    spinner_employeedept.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            try {
                                empByDepartment = dataListEmp.get(position);

                                departmentsEmp = empByDepartment.getName();
                                String departmentsValueCode = empByDepartment.getEmpCode();
                                empMobileNo = empByDepartment.getMobileNumber();

                                tv_emp_code.setText(departmentsValueCode);


                                Log.d("departmentsEmp", New_visitordetail.this.departmentsEmp);

                            } catch (Exception e) {

                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                } else {
                    Toast.makeText(New_visitordetail.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(@NonNull Call<EmployeeByDepartment> call, @NonNull Throwable t) {

                // pDialog.dismiss();
                Log.d("Error", t.getMessage());
            }
        });
    }

    private void getBranches() {

        Runnable runnable = new Runnable() {
            public void run() {
                progressDialogInitialisaton();

                handler.postDelayed(this, 5000);
            }
        };
        runnable.run();

        APIService service = ApiClient.getClient().create(APIService.class);
        Call<Branches> call = service.getBranches("Bearer " + prefConfig.readToken());
        call.enqueue(new Callback<Branches>() {
            @Override
            public void onResponse(@NonNull Call<Branches> call, @NonNull retrofit2.Response<Branches> response) {
                if (response.body() != null) {
                    try {
                        if (response.body().getMessage().equalsIgnoreCase("Branches List")) {

                            Log.d("Branchessssss", "" + response.body().getData());

                            List<Branches.Data> dataList = response.body().getData();
                            for (int i = 0; i < dataList.size(); i++) {
                                Log.d("kjxngksjnkjsdn", dataList.toString());

//                                HashSet<String> hashSet = new HashSet<String>();
//                                hashSet.addAll(listBranches);
//                                listBranches.clear();
//                                listBranches.addAll(hashSet);
                                listBranches.add(dataList.get(i).getBranchName());

                            }
                            //Creating the ArrayAdapter instance having the country list
                            ArrayAdapter aa = new ArrayAdapter(New_visitordetail.this, android.R.layout.simple_spinner_item, listBranches);
                            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            //Setting the ArrayAdapter data on the Spinner
                            spinner_branches.setAdapter(aa);
                            aa.notifyDataSetChanged();
                            handler.removeCallbacks(runnable);
                            pDialog.dismiss();


                        }

                    } catch (Exception e) {

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

    private void progressDialogInitialisaton() {

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading Data Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();
    }

    @Override
    public void onClick(View v) {

        if (v == btnTimePickerIn) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            txtTimeIn.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
        if (v == btnTimePickerOut) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            txtTimeOut.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {

            if (requestCode == 1888) {

                try {
                    Bitmap photo = (Bitmap) data.getExtras().get("data");
                    visitorPhoto.setImageBitmap(photo);
                    uploading_bitmap = photo;

                    //Remove Image
                    visitorPhoto.setBackgroundResource(android.R.color.transparent);

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
                visitorPhoto.setImageBitmap(thumbnail);
                uploading_bitmap = thumbnail;
            }
        }
    }

    private void registrationApi(File file) {

        RequestBody reqFile1 = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part image1 = MultipartBody.Part.createFormData("image", file.getName(), reqFile1);

        APIService service = ApiClient.getClient().create(APIService.class);
        Call<ResponseBody> call = service.getVisitorRequest(prefConfig.readToken(), visitorName,
                Visitor_mobile_no, "1234", Purposeofcomeing,
                pin_code.getText().toString().trim(),
                tv_spinner_state.getText().toString(), cityValue,
                TxtTimeIn, TxtTimeout, tv_emp_code.getText().toString(),
                departmentsEmp,
                empMobileNo, image1, image1);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.body() != null) {

                    if (response.body().toString().equalsIgnoreCase("Visitor Created Successfully")) {

                    Intent intent = new Intent(New_visitordetail.this,Visitorrequestcome_to_emplpyee.class);
                    startActivity(intent);
                    }
                } else {
                    Toast.makeText(New_visitordetail.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                // pDialog.dismiss();
                Log.d("Error", t.getMessage());
            }
        });
    }

}