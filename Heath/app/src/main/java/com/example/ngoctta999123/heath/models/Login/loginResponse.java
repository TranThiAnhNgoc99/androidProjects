package com.example.ngoctta999123.heath.models.Login;

import com.example.ngoctta999123.heath.models.User;

public class loginResponse {
    private boolean error;
    private  String message;
    private User user;

    public loginResponse(boolean error, String message, User user) {
        this.error = error;
        this.message = message;
        this.user = user;
    }

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }
}
