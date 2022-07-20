package com.example.provider.service.impl;

import com.example.api.service.LoginService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

/**
 * @projectName: dubbo_demo
 * @package: com.example.provider.impl
 * @className: LoginServiceImpl
 * @author: LiYinjian
 * @date: 2022/7/19 12:50
 * @version: 1.0
 */

@Component
@DubboService
public class LoginProviderServiceImpl implements LoginService {

    public static final Pattern mobilePattern = Pattern.compile("^1(3[0-9]|5[0-3,5-9]|7[1-3,5-8]|8[0-9])\\d{8}$");

    @Override
    public String login(String username, String password) {
        if (username == null || !StringUtils.hasLength(username)) {
            return "用户名不能为空";
        }
        if (mobilePattern.matcher(username).matches() == false) {
            return "用户名应为手机号码";
        }
        if (password == null || !StringUtils.hasLength(password)) {
            return "密码不能为空";
        }
        if (password.length() > 16) {
            return "密码长度请小于16位";
        }
        if (username.equals("15062427921") && password.equals("nihao")) {
            return "yes";
        }
        return "请重新输入";
    }
}
