package com.example.markettechchallenge.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProviders;

import com.example.markettechchallenge.R;
import com.example.markettechchallenge.ui.orders.OrderActivity;


public class LoginActivity extends AppCompatActivity {
    boolean openClose;

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

       /* CardView cardView = findViewById(R.id.card_view);
        final LinearLayout linearLayout = findViewById(R.id.detail);
         openClose = false;
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(openClose){
                    linearLayout.setVisibility(View.GONE);
                    openClose = false;

                }else{
                    linearLayout.setVisibility(View.VISIBLE);
                    openClose = true;
                }
            }
        });*/

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTextUsername.getText().toString().equals(username) && editTextPass.getText().toString().equals(pass)){
                    if (rememberMe.isChecked()){
                        userRegister(editTextUsername.getText().toString(),editTextPass.getText().toString());
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
    private void userRegister(String username,String pass){
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
