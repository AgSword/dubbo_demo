package com.example.api.dto;

import com.example.api.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description
 * @projectName: dubbo_demo
 * @package: com.example.api.dto
 * @className: CountAboutBlogDTO
 * @author: LiYinjian
 * @date: 2022/8/1 15.50
 * @version: 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountAboutBlogDTO implements Serializable {
    private Integer userId;
    private Integer type;
    private StatusEnum status;
    private Date startTime;
    private Date endTime;
}
