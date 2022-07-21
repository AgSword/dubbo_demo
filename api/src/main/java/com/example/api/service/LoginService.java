package com.example.api.service;

import com.example.api.vo.LoginVo;

import java.io.IOException;

public interface LoginService {
    Object login(LoginVo loginVo) throws IOException;
}
