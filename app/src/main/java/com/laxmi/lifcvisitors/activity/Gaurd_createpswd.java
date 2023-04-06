package com.laxmi.lifcvisitors.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.laxmi.lifcvisitors.R;
import com.laxmi.lifcvisitors.retrofitservices.APIService;
import com.laxmi.lifcvisitors.retrofitservices.ApiClient;

import org.json.JSONArray;
import org.json.JSONObject;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Gaurd_createpswd extends AppCompatActivity {
    Intent intent;
    TextView tv_createpsw;
    EditText ev_new_password, ev_confirm_password;
    String mob_no, emp_code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaurd_createpswd);
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
                if(!newPassword.isEmpty() && !confirmPassword.isEmpty()){
                    if (!newPassword.equals(confirmPassword)) {
                        Toast.makeText(Gaurd_createpswd.this, "Password not matched !!", Toast.LENGTH_SHORT).show();
                    } else {
                        registrationApi(newPassword);
                    }
                }else {
                    Toast.makeText(Gaurd_createpswd.this, "Enter Password !!", Toast.LENGTH_SHORT).show();

                }


//                intent = new Intent(Gaurd_createpswd.this,GaurdLogin.class);
//                startActivity(intent);
            }
        });
        TextView tv = (TextView) this.findViewById(R.id.mywidget);
        tv.setSelected(true);
    }

    private void registrationApi(String newPassword) {
        APIService service = ApiClient.getClient().create(APIService.class);
        Call<ResponseBody> call = service.getSignup(mob_no, emp_code, "1234", newPassword);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
//                    assert response.body() != null;
                    JSONArray myListsAll = new JSONArray("[" + response.body().string() + "]");
                    for (int i = 0; i < myListsAll.length(); i++) {
                        JSONObject jsonobject = (JSONObject) myListsAll.get(i);
                        String string_message = jsonobject.getString("message");
                        Toast.makeText(Gaurd_createpswd.this, string_message, Toast.LENGTH_SHORT).show();

                        if (string_message.equalsIgnoreCase("Guard Registed Successfully")) {
                            Intent intents = new Intent(Gaurd_createpswd.this, GaurdLogin.class);
                            startActivity(intents);
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
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