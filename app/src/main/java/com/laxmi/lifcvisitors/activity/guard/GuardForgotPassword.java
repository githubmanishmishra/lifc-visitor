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

import com.laxmi.lifcvisitors.R;
import com.laxmi.lifcvisitors.model.MSG;
import com.laxmi.lifcvisitors.retrofitservices.APIService;
import com.laxmi.lifcvisitors.retrofitservices.ApiClient;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GuardForgotPassword extends AppCompatActivity {

    TextView tv_change_password;
    ImageView iv_back;
    EditText ev_new_password, ev_confirm_password;
    String mob_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaurdforgetpswd);
     ImageView   iv_back = findViewById(R.id.iv_back);
     iv_back.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             finish();
         }
     });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mob_no = bundle.getString("mob_no");
        }

        tv_change_password = findViewById(R.id.tv_gaurdchangepwd);
        ev_confirm_password = findViewById(R.id.ev_confirm_password);
        ev_new_password = findViewById(R.id.ev_new_password);


        tv_change_password.setOnClickListener(view -> {
            String newPassword = ev_new_password.getText().toString();
            String confirmPassword = ev_confirm_password.getText().toString();

            if (newPassword.length() < 7 && !isValidPassword(newPassword)) {
                //1 Uppercase, 1 Number and 1 Symbol and at least 8 character
//                    System.out.println("Not Valid");
                TextView tv_password_pattern = findViewById(R.id.tv_password_pattern);
                tv_password_pattern.setVisibility(View.VISIBLE);
//                tv_pattern.setText(getString(R.string.content));
            } else {

                if (!newPassword.isEmpty() && !confirmPassword.isEmpty()) {
                    if (!newPassword.equals(confirmPassword)) {
                        Toast.makeText(GuardForgotPassword.this, "Password not matched !!", Toast.LENGTH_SHORT).show();
                    } else {
                        forgotPasswordApi(newPassword);

                    }
                } else {
                    Toast.makeText(GuardForgotPassword.this, "Enter Password !!", Toast.LENGTH_SHORT).show();

                }
                System.out.println("Valid");
            }

        });
        TextView tv = (TextView) this.findViewById(R.id.mywidget);
        tv.setSelected(true);
    }

    private void forgotPasswordApi(String newPassword) {

        Log.d("jhjvkvkvjkvjkgkv",mob_no);

        APIService service = ApiClient.getClient().create(APIService.class);
        Call<MSG> call = service.getForgotPassword(mob_no, "1234", newPassword);
        call.enqueue(new Callback<MSG>() {
            @Override
            public void onResponse(@NonNull Call<MSG> call, @NonNull Response<MSG> response) {

                if (response.body() != null) {

                    if (response.body().getMessage().equalsIgnoreCase("Guard Password Update Successfully")) {

                        Intent intents = new Intent(GuardForgotPassword.this, GaurdLogin.class);
                        startActivity(intents);
                        Toast.makeText(GuardForgotPassword.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(GuardForgotPassword.this, "Mobile Number Invalid", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(@NonNull Call<MSG> call, @NonNull Throwable t) {

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