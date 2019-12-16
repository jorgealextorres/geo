package com.example.geo.utils.mapper;

import com.google.gson.Gson;

public class JsonMapper {

    public static String getJsonFromObject(Object obj){
        Gson gson = new Gson();
        return gson.toJson(obj);
    }
}
