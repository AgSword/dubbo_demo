package com.example.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description
 * @projectName: dubbo_demo
 * @package: com.example.provider.dto
 * @className: TypeCountDTO
 * @author: LiYinjian
 * @date: 2022/8/1 14:09
 * @version: 1.0
 */

@Data
@AllArgsConstructor
public class TypeCountDTO implements Serializable {
    private String name;
    private Integer num;
}
