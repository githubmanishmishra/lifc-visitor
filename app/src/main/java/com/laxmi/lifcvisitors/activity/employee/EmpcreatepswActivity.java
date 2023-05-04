package com.laxmi.lifcvisitors.activity.employee;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.messaging.FirebaseMessaging;
import com.laxmi.lifcvisitors.R;
import com.laxmi.lifcvisitors.activity.guard.GaurdLogin;
import com.laxmi.lifcvisitors.activity.guard.Guard_createpswd;
import com.laxmi.lifcvisitors.model.MSG;
import com.laxmi.lifcvisitors.retrofitservices.APIService;
import com.laxmi.lifcvisitors.retrofitservices.ApiClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmpcreatepswActivity extends AppCompatActivity {
    Intent intent;
    TextView confirmpsw;

    TextInputEditText ev_confirm_password, ev_new_password;
    String mob_no, emp_code;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empcreatepsw);
        confirmpsw = findViewById(R.id.tv_confirmpsw);
        ev_confirm_password = findViewById(R.id.ev_confirm_password);
        ev_new_password = findViewById(R.id.ev_new_password);

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
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mob_no = bundle.getString("mob_no");
            emp_code = bundle.getString("emp_code");
        }
        confirmpsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newPassword = ev_new_password.getText().toString();
                String confirmPassword = ev_confirm_password.getText().toString();

                if (newPassword.length() < 7 && !isValidPassword(newPassword)) {
                    //1 Uppercase, 1 Number and 1 Symbol and at least 8 character
//                    System.out.println("Not Valid");
                    TextView tv_pattern = findViewById(R.id.tv_password_pattern);
                    tv_pattern.setText(getString(R.string.content));
                } else {

                    registrationApi(newPassword);

                    if (!newPassword.isEmpty() && !confirmPassword.isEmpty()) {
                        if (!newPassword.equals(confirmPassword)) {
                            Toast.makeText(EmpcreatepswActivity.this, "Password not matched !!", Toast.LENGTH_SHORT).show();
                        } else {
                        }
                    } else {
                        Toast.makeText(EmpcreatepswActivity.this, "Enter Password !!", Toast.LENGTH_SHORT).show();

                    }
                    System.out.println("Valid");
                }

            }
        });
        TextView tv = this.findViewById(R.id.mywidget);
        tv.setSelected(true);
    }

    private void registrationApi(String newPassword) {
        APIService service = ApiClient.getClient().create(APIService.class);
        Call<MSG> call = service.getEmployeeSignup(mob_no, emp_code, "1234",
                newPassword, token);
        call.enqueue(new Callback<MSG>() {
            @Override
            public void onResponse(Call<MSG> call, Response<MSG> response) {
/*
                try {
                    assert response.body() != null;
                    JSONArray myListsAll = new JSONArray("[" + response.body().string() + "]");
                    for (int i = 0; i < myListsAll.length(); i++) {
                        JSONObject jsonobject = (JSONObject) myListsAll.get(i);
                        String string_message = jsonobject.getString("message");
                        Toast.makeText(EmpcreatepswActivity.this, string_message, Toast.LENGTH_SHORT).show();

                        if (string_message.equalsIgnoreCase("Employee Registed Successfully")) {
                            Intent intents = new Intent(EmpcreatepswActivity.this, EmployeeLogin.class);
                            startActivity(intents);
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }*/
                assert response.body() != null;
                String string_message = response.body().getMessage();
                Toast.makeText(EmpcreatepswActivity.this, string_message, Toast.LENGTH_SHORT).show();

                if (string_message.equalsIgnoreCase("Employee Registed Successfully")) {
                    Intent intents = new Intent(EmpcreatepswActivity.this, EmployeeLogin.class);
                    startActivity(intents);
                }
            }

            @Override
            public void onFailure(Call<MSG> call, Throwable t) {

                // pDialog.dismiss();
                Log.d("Error", t.getMessage());
            }
        });
    }

    //Strong Password Generation
    public static boolean isValidPassword(final String password) {
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();

    }
}