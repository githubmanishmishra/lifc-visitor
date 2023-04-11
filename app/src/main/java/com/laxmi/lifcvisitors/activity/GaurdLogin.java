package com.laxmi.lifcvisitors.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.laxmi.lifcvisitors.R;
import com.laxmi.lifcvisitors.model.MSG;
import com.laxmi.lifcvisitors.retrofitservices.APIService;
import com.laxmi.lifcvisitors.retrofitservices.ApiClient;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GaurdLogin extends AppCompatActivity {
    TextView tv_forget;
    Intent intent;
    TextView tv_login, registration_text;
    EditText ev_empcodes, ev_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaurd_login);
        TextView  tv = (TextView) this.findViewById(R.id.mywidget);
        tv.setSelected(true);
        tv_forget = findViewById(R.id.forgot_pwd_gaurd);
        registration_text = findViewById(R.id.registration_text);
        tv_forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent    intents = new Intent(GaurdLogin.this,Gaurd_mobileno_forget.class);
                startActivity(intents);
            }
        });
        registration_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent  intents = new Intent(GaurdLogin.this, Gaurdregistration.class);
                startActivity(intents);

            }
        });
        tv_login = findViewById(R.id.tv_login);
        ev_empcodes = findViewById(R.id.ev_empcode);
        ev_password = findViewById(R.id.ev_password);
        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                if (ev_empcodes.getText().toString().isEmpty() &&
                        ev_empcodes.getText().toString().length() != 10
                        && ev_password.getText().toString().isEmpty()) {
                    Toast.makeText(GaurdLogin.this, "Enter 10 digit mobile no. and password", Toast.LENGTH_SHORT).show();
                } else {
                    String emp_code = ev_empcodes.getText().toString();
                    String emp_Password = ev_password.getText().toString();
                    getLogin(emp_code, emp_Password);
                }
            }
        });
    }

    private void getLogin(String emp_code, String emp_Password) {
        APIService service = ApiClient.getClient().create(APIService.class);
        Call<MSG> call = service.getLogin(emp_code, emp_Password);
        call.enqueue(new Callback<MSG>() {
            @Override
            public void onResponse(@NonNull Call<MSG> call, @NonNull Response<MSG> response) {

                if (response.body() != null) {
            /*        Log.d("Mainsj Mishra", "" + response.body().getMessage());

                    Log.d("token>>>>", response.body().getToken());*/
                    if (response.body().getMessage().equalsIgnoreCase("Guard Login Successfully")) {
                        Intent intents = new Intent(GaurdLogin.this, GuardDashboard.class);
                        startActivity(intents);
                        Toast.makeText(GaurdLogin.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else  {
                    Toast.makeText(GaurdLogin.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();

                }



               /* try {
//                    assert response.body() != null;
                    JSONObject myListsAll = new JSONObject(response.body().string());
                        String string_message = myListsAll.getString("message");
                        String token = myListsAll.getString("token");
                        Log.d("token>>>>", token);
                        Toast.makeText(GaurdLogin.this, string_message, Toast.LENGTH_SHORT).show();
                        if(response.isSuccessful()){
                            if (string_message.equalsIgnoreCase("Guard Login Successfully")) {
                                Intent intents = new Intent(GaurdLogin.this, GuardDashboard.class);
                                startActivity(intents);
//                            {"status":"error","message":"Somthing Went Wrong"}
                            }
                        }else  if (string_message.equalsIgnoreCase("Somthing Went Wrong")){
                                Toast.makeText(GaurdLogin.this, "Wrong credentials", Toast.LENGTH_SHORT).show();

                        }

                } catch (Exception e) {
//                    e.printStackTrace();
                    Log.d("Error", ""+e.getMessage());
                }*/
            }

            @Override
            public void onFailure(@NonNull Call<MSG> call, @NonNull Throwable t) {

                // pDialog.dismiss();
                Log.d("Error", t.getMessage());
            }
        });
    }
}