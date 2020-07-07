package com.example.ngoctta999123.heath.Activities.Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.ngoctta999123.heath.Activities.MainActivity;
import com.example.ngoctta999123.heath.R;
import com.example.ngoctta999123.heath.Retrofit2.APIUtils;
import com.example.ngoctta999123.heath.Retrofit2.DataClient;
import com.example.ngoctta999123.heath.models.Login.LoginModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    RelativeLayout relativeLayout;
    LinearLayout linearLayout;
    ConstraintLayout constraintLayout;
    EditText edtUserName;
    EditText edtPassWord;
    Button btnLogin;
    String password, email;
    SharedPreferences sharedPreferences;
    private final String TOKEN_NAME = "TOKEN";
    private  final String ACCESS_TOKEN = "ACCESS_TOKEN";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        anhXa();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = edtUserName.getText().toString().trim();
                password = edtPassWord.getText().toString().trim();

                if (email.length() > 0 && password.length() > 0) {
                    LoginModel loginModel = new LoginModel(email, password);
                    DataClient dataClient = APIUtils.getData(getApplicationContext());
                    Call<String> callback = dataClient.loginData(loginModel);
                    relativeLayout.setVisibility(View.VISIBLE);
                    linearLayout.setVisibility(View.VISIBLE);
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String uid = response.body();
                            if (uid != null) {
                                relativeLayout.setVisibility(View.GONE);
                                linearLayout.setVisibility(View.GONE);

                                sharedPreferences = getSharedPreferences(TOKEN_NAME, Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString(ACCESS_TOKEN, uid);
                                editor.apply();

                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                Toast.makeText(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                            } else {
                                relativeLayout.setVisibility(View.GONE);
                                linearLayout.setVisibility(View.GONE);
                                Toast.makeText(LoginActivity.this, "Tài khoản sai!!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            relativeLayout.setVisibility(View.GONE);
                            linearLayout.setVisibility(View.GONE);
                            Toast.makeText(LoginActivity.this, "loi!!", Toast.LENGTH_SHORT).show();
                            Log.d("loginfaild", "onFailure: ");
                        }
                    });
                } else {
                    Toast.makeText(LoginActivity.this, "Vui lòng điền đầy đủ thông tin để đăng nhập!!", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    private void anhXa() {
        edtUserName = findViewById(R.id.editTextUserName);
        edtPassWord = findViewById(R.id.editTextPassword);
        btnLogin = findViewById(R.id.buttonLogin);
        relativeLayout = findViewById(R.id.relativeLayoutLogin);
        linearLayout = findViewById(R.id.linearProgress);
        constraintLayout = findViewById(R.id.consLogin);
    }

}