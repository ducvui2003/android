package com.example.truyenapp.utils;

import com.example.truyenapp.model.JWTToken;
import com.example.truyenapp.response.UserResponse;

import java.sql.Timestamp;
import java.util.Date;

public class AuthenticationManager {
    public static UserResponse userResponse;

    public static boolean isLoggedIn(JWTToken token) {
        if (token == null)
            return false;
        return new Timestamp(System.currentTimeMillis()).before(new Timestamp(token.getExpiredTime()));
    }
}
