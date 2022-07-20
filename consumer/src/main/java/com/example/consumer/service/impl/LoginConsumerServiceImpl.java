package com.example.consumer.service.impl;

import com.example.api.service.LoginService;
import com.example.consumer.service.LoginConsumerService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
public class LoginConsumerServiceImpl implements LoginConsumerService {

    @DubboReference
    LoginService loginService;

    @Override
    public String login(String username, String password) {
        return loginService.login(username, password);
    }
}
