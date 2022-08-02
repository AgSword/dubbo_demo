package com.example.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;



/**
 * @Description
 * @projectName: dubbo_demo
 * @package: com.example.provider.dto
 * @className: BlogCountDTO
 * @author: LiYinjian
 * @date: 2022/8/1 15:22
 * @version: 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogCountDTO implements Serializable {
    private String time;
    private Integer num;
}
