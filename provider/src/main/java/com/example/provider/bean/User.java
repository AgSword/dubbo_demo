package com.example.provider.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @projectName: dubbo_demo
 * @package: com.example.provider.bean
 * @className: User
 * @author: LiYinjian
 * @date: 2022/7/21 9:53
 * @version: 1.0
 */

@Data
@TableName("t_user")
public class User {
    private String username;
    private String password;
}
