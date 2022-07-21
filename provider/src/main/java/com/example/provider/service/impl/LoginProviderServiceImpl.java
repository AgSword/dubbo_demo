package com.example.provider.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.api.service.LoginService;
import com.example.api.vo.LoginVo;
import com.example.provider.bean.Score;
import com.example.provider.bean.User;
import com.example.provider.service.ScoreService;
import com.example.provider.service.UserService;
import com.example.provider.util.MD5Util;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    @Autowired
    ScoreService scoreService;

    @Autowired
    UserService userService;

    @Override
    public Object login(LoginVo loginVo) {
        String username = loginVo.getUsername();
        String password = loginVo.getPassword();
        User user = userService.getOne(new QueryWrapper<User>().eq("username",username));
        if (user==null){
            return "用户不存在";
        }
        if (!MD5Util.MD5Encrypt(password).equals(user.getPassword())){
            return "密码错误";
        }
        Score score = scoreService.getOne(new QueryWrapper<Score>().eq("username", username));
        return score;
    }
}
