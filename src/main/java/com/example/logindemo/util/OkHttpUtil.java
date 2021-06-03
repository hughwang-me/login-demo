package com.example.logindemo.util;

import okhttp3.*;

import java.io.IOException;
import java.util.Map;

public class OkHttpUtil {

    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public String post(String url, Map<String , String> headers , Map<String , String> params) throws IOException {
        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        params.forEach(formBodyBuilder::add);
        FormBody formBody = formBodyBuilder.build();
        Request.Builder requestBuilder = new Request.Builder()
                .url(url)
                .post(formBody);
        headers.forEach(requestBuilder::addHeader);
        Request request = requestBuilder.build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
