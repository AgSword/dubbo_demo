package com.example.consumer.vo;

import com.example.api.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description
 * @projectName: dubbo_demo
 * @package: com.example.consumer.vo
 * @className: CountAboutBlogVo
 * @author: LiYinjian
 * @date: 2022/8/1 14:39
 * @version: 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountAboutBlogVo implements Serializable {
    private Integer userId;
    private Integer type;
    private StatusEnum status;
    private Date startTime;
    private Date endTime;
}
