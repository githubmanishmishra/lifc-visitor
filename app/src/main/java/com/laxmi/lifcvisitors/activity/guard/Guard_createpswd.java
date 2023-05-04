package com.laxmi.lifcvisitors.activity.guard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.messaging.FirebaseMessaging;
import com.laxmi.lifcvisitors.R;
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

public class Guard_createpswd extends AppCompatActivity {
    Intent intent;
    TextView tv_createpsw;
    EditText ev_new_password, ev_confirm_password;
    String mob_no, emp_code;
    String  token;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaurd_createpswd);
        ImageView iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
        tv_createpsw = findViewById(R.id.tv_confirmpsw);
        ev_new_password = findViewById(R.id.ev_new_password);
        ev_confirm_password = findViewById(R.id.ev_confirm_password);

        tv_createpsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newPassword = ev_new_password.getText().toString();
                String confirmPassword = ev_confirm_password.getText().toString();

                if (newPassword.length() < 7 && !isValidPassword(newPassword)) {
                    //1 Uppercase, 1 Number and 1 Symbol and at least 8 character
//                    System.out.println("Not Valid");
                    TextView tv_pattern = findViewById(R.id.tv_password_pattern);
                    tv_pattern.setText(getString(R.string.content));
                }
                else {

                    registrationApi(newPassword);

                    if (!newPassword.isEmpty() && !confirmPassword.isEmpty()) {
                        if (!newPassword.equals(confirmPassword)) {
                            Toast.makeText(Guard_createpswd.this, "Password not matched !!", Toast.LENGTH_SHORT).show();
                        } else {
                        }
                    } else {
                        Toast.makeText(Guard_createpswd.this, "Enter Password !!", Toast.LENGTH_SHORT).show();

                    }
                    System.out.println("Valid");
                }

            }
        });
        TextView tv = (TextView) this.findViewById(R.id.mywidget);
        tv.setSelected(true);
    }

    private void registrationApi(String newPassword) {
        APIService service = ApiClient.getClient().create(APIService.class);
        Call<MSG> call = service.getSignup(mob_no, emp_code, "1234",
                newPassword,token);
        call.enqueue(new Callback<MSG>() {
            @Override
            public void onResponse(@NonNull Call<MSG> call, @NonNull Response<MSG> response) {
                assert response.body() != null;
                String string_message = response.body().getMessage();
                Toast.makeText(Guard_createpswd.this, string_message, Toast.LENGTH_SHORT).show();
                if (string_message.equalsIgnoreCase("Guard Registed Successfully")) {
                    Intent intents = new Intent(Guard_createpswd.this, GaurdLogin.class);
                    startActivity(intents);
                }
                else {
                    Toast.makeText(Guard_createpswd.this, "Guard Already Exist", Toast.LENGTH_SHORT).show();
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