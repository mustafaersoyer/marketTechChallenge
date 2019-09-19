package com.example.markettechchallenge.ui.login;
import androidx.lifecycle.ViewModel;

import com.example.markettechchallenge.data.model.User;

public class LoginViewModel extends ViewModel {

    User user = new User();
    public void setUserEmail(String username) {
        user.setUsername(username);
    }


    public String getUsername() {
        return user.getUsername();
    }

    public void setPass(String pass) {
        user.setPass(pass);
    }


    public String getPass() {
        return user.getPass();
    }
}
