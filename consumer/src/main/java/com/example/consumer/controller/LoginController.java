package com.example.consumer.controller;

import com.example.consumer.service.LoginConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @projectName: dubbo_demo
 * @package: com.example.consumer.controller
 * @className: LoginController
 * @author: LiYinjian
 * @date: 2022/7/19 14:13
 * @version: 1.0
 */

@RestController
public class LoginController {
    @Autowired
    LoginConsumerService loginConsumerService;

    @GetMapping("/")
    public String login(String username,String password){

        return loginConsumerService.login(username,password);
    }
}
