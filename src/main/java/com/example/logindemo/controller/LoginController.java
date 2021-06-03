package com.example.logindemo.controller;

import com.example.logindemo.domain.LoginDomain;
import com.example.logindemo.util.GsonUtil;
import com.example.logindemo.util.OkHttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
@Service
@Slf4j
public class LoginController {

    @PostMapping(value = "login")
    public String login(@RequestBody LoginDomain loginDomain){
        log.warn("登录参数：{}" , GsonUtil.toJson(loginDomain));
        String clientInfo = "client:123456";
        String basicInfo = "Basic " + Base64.getEncoder().encodeToString(clientInfo.getBytes(StandardCharsets.UTF_8));
        Map<String ,String> headers = new HashMap<>();
        headers.put("Authorization" , basicInfo);
        Map<String ,String> params = new HashMap<>();
        params.put("username" , loginDomain.getUsername());
        params.put("password" , loginDomain.getPassword());
        params.put("grant_type" , "password");

        OkHttpUtil okHttpUtil = new OkHttpUtil();
        String url = "http://rx.uwjx.com:9890/zjxf-gateway/zjxf-auth-server/oauth/token";
        String resp = null;
        try {
            resp = okHttpUtil.post(url , headers , params);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resp;
    }
}
