package com.example.truyenapp.utils;

import com.example.truyenapp.model.JWTToken;

import java.sql.Timestamp;
import java.util.Date;

public class AuthenticationManager {

    public static boolean isLoggedIn(JWTToken token) {
        if (token == null)
            return false;
        return new Timestamp(System.currentTimeMillis()).before(new Timestamp(token.getExpiredTime()));
    }
}
