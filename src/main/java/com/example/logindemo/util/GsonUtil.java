package com.example.logindemo.util;

import com.google.gson.Gson;

public class GsonUtil {

    public static String toJson(Object obj){
        Gson gson = new Gson();
        return gson.toJson(obj);
    }
}
