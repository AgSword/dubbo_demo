package com.example.provider.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.provider.bean.User;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper extends BaseMapper<User> {
}
