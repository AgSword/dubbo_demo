package com.example.consumer.controller;

import com.example.api.service.LoginService;
import com.example.api.vo.LoginVo;
import com.example.consumer.vo.RespVo;
import com.example.consumer.vo.RespVoEnum;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

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
    @DubboReference
    LoginService loginService;

    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public RespVo login(@Valid @RequestBody LoginVo loginVo) throws IOException {
        Object o = loginService.login(loginVo);
        if (o instanceof String) {
            String s = (String) o;
            if (s.equals("用户不存在")) {
                return RespVo.error(RespVoEnum.UserNoExist_Error);
            }
            if (s.equals("密码错误")) {
                return RespVo.error(RespVoEnum.Password_Error);
            }
        }
        return RespVo.success(RespVoEnum.Success, o);
    }
}
