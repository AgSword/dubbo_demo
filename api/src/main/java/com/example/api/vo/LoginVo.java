package com.example.api.vo;


import com.example.api.validator.IsMobile;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @projectName: dubbo_demo
 * @package: com.example.provider.vo
 * @className: LoginVo
 * @author: LiYinjian
 * @date: 2022/7/20 13:49
 * @version: 1.0
 */

@Data
@Setter
@Getter
@ToString
public class LoginVo implements Serializable {

    @IsMobile
    @NotNull
    String username;
    @NotNull
    @Length(min = 6, max = 16)
    String password;
}
