package com.laxmi.lifcvisitors.activity.employee;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.messaging.FirebaseMessaging;
import com.laxmi.lifcvisitors.R;
import com.laxmi.lifcvisitors.activity.guard.GaurdLogin;
import com.laxmi.lifcvisitors.activity.guard.GuardDashboard;
import com.laxmi.lifcvisitors.model.MSG;
import com.laxmi.lifcvisitors.retrofitservices.APIService;
import com.laxmi.lifcvisitors.retrofitservices.ApiClient;
import com.laxmi.lifcvisitors.savedata.PrefConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeLogin extends AppCompatActivity {
    TextView tv_forget;
    TextView tv_login, registration_text;
    EditText ev_empcodes, ev_password;
    public static PrefConfig prefConfig;

    String  token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employeelogin);

        prefConfig = new PrefConfig(this);

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

        tv_login = findViewById(R.id.tv_login);
        TextView tv = findViewById(R.id.mywidget);
        tv.setSelected(true);
        tv_forget = findViewById(R.id.forgot_pwd_emp);
        registration_text = findViewById(R.id.registration_text);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tv_forget.setOnClickListener(view -> {
            Intent intent = new Intent(EmployeeLogin.this, Forgetpsw.class);
            startActivity(intent);
        });
        registration_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmployeeLogin.this, EmployeeSignup.class);
                startActivity(intent);
            }
        });
       /* registration_text.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View arg0, boolean arg1) {
                // TODO Auto-generated method stub
                arg0.setBackgroundColor(Color.BLACK);
            }
        });*/
        tv_login = findViewById(R.id.tv_login);
        ev_empcodes = findViewById(R.id.ev_emp_login);
        ev_password = this.<EditText>findViewById(R.id.ev_password);
       // String deviceToken="";
        tv_login.setOnClickListener(view -> {
            if (ev_empcodes.getText().toString().isEmpty() &&
                    ev_empcodes.getText().toString().length() != 10
                    && ev_password.getText().toString().isEmpty()) {
                Toast.makeText(EmployeeLogin.this, "Enter 10 digit mobile no. and password", Toast.LENGTH_SHORT).show();
            }
            else

            {
                String emp_code = ev_empcodes.getText().toString();
                String emp_Password = ev_password.getText().toString();
                getLogin(emp_code, emp_Password,token);
            }
        });
    }

    private void getLogin(String emp_code, String emp_Password,String deviceToken) {
        APIService service = ApiClient.getClient().create(APIService.class);
        Call<MSG> call = service.getLogin(emp_code, emp_Password, deviceToken);
        call.enqueue(new Callback<MSG>() {
            @Override
            public void onResponse(@NonNull Call<MSG> call, @NonNull Response<MSG> response) {
                if (response.body() != null) {
            /*        Log.d("Mainsj Mishra", "" + response.body().getMessage());

                    Log.d("token>>>>", response.body().getToken());*/
                    if (response.body().getMessage().equalsIgnoreCase("Login Successfully")) {
                        if(response.body().getType().equalsIgnoreCase("Employee")){
                            prefConfig.writeLoginStatus(true);
                            prefConfig.writeName("Guard", response.body().getToken(), response.body().getType());
                            Log.d("token>>>>>>>>>>>>", response.body().getToken());

                            Intent intents = new Intent(EmployeeLogin.this, EmployeeDashboard.class);
                            startActivity(intents);
                            Toast.makeText(EmployeeLogin.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else
                {
                    Toast.makeText(EmployeeLogin.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(@NonNull Call<MSG> call, @NonNull Throwable t) {

                // pDialog.dismiss();
                Log.d("Error", t.getMessage());
            }
        });
    }
}
