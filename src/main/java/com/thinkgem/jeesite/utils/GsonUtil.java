package com.thinkgem.jeesite.utils;

import java.text.DateFormat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil {

    private static GsonBuilder gsonBuilder = new GsonBuilder();
    private static Gson gson = new Gson();

    static {
        gsonBuilder.registerTypeAdapter(java.util.Date.class, new DateSerializerUtils());
        gsonBuilder.registerTypeAdapter(java.util.Date.class, new DateDeserializerUtils());
        gsonBuilder.setDateFormat(DateFormat.LONG);
        gson = gsonBuilder.create();
    }

    public static Gson getGson() {
        return gson;
    }

    public static String toJson(Object src) {

        return gson.toJson(src);
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

    public static void main(String[] args) {
        System.out.println(GsonUtil.toJson(3));
    }
}
