package com.example.markettechchallenge.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.markettechchallenge.R;
import com.example.markettechchallenge.ui.orders.OrderActivity;


public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    EditText editTextUsername,editTextPass;
    LoginViewModel loginViewModel;
    Switch rememberMe;
    String username,pass;
    SharedPreferences sharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = this.getSharedPreferences("sharedPref",Context.MODE_PRIVATE);
        sessionCheck();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        viewBind();
        loginViewModel =
                ViewModelProviders.of(this).get(LoginViewModel.class);
        username = loginViewModel.getUsername();
        pass = loginViewModel.getPass();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTextUsername.getText().toString().equals(username) && editTextPass.getText().toString().equals(pass)){
                    if (rememberMe.isChecked()){
                        setSession(editTextUsername.getText().toString(),editTextPass.getText().toString());
                        startActivity(new Intent(LoginActivity.this, OrderActivity.class));
                    }else{
                        startActivity(new Intent(LoginActivity.this, OrderActivity.class).putExtra("session",1));
                    }
                }else{
                    Toast.makeText(LoginActivity.this, "Hatalı Kullanıcı Adı veya Şifre!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void viewBind(){
        btnLogin = findViewById(R.id.btnLogin);
        editTextUsername = findViewById(R.id.username);
        editTextPass = findViewById(R.id.pass);
        rememberMe = findViewById(R.id.rememberMe);
    }
    private void setSession(String username,String pass){
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("username",username);
        editor.putString("pass",pass);
        editor.commit();
    }
    private void sessionCheck(){
        if (sharedPref.getString("username",null) != null || sharedPref.getString("pass",null) != null){
            startActivity(new Intent(getApplicationContext(), OrderActivity.class));
        }
    }
}
