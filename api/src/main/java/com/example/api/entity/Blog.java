package com.example.api.entity;

import com.example.api.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Convert;
import java.io.Serializable;
import java.util.Date;

/**
 * @projectName: dubbo_demo
 * @package: com.example.api.entity
 * @className: Blog
 * @author: LiYinjian
 * @date: 2022/7/28 10:54
 * @version: 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blog implements Serializable {
    private Integer id;
    private Integer userId;
    private String title;
    private String content;
    private Date createTime;
    private Date updateTime;
    private Integer type;
    //枚举类
    private StatusEnum status;
}
