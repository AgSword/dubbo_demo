package com.example.provider.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.provider.bean.User;
import com.example.provider.mapper.UserMapper;
import com.example.provider.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @projectName: dubbo_demo
 * @package: com.example.provider.service.impl
 * @className: UserServiceImpl
 * @author: LiYinjian
 * @date: 2022/7/21 9:57
 * @version: 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
