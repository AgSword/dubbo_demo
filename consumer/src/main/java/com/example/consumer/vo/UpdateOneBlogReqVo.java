package com.example.consumer.vo;

import com.example.api.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Convert;
import javax.persistence.Converter;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 对blog进行修改错误，不应该对create_time字段进行修改，所以需要新建这个vo对象
 * id属性不能为空
 *
 * @projectName: dubbo_demo
 * @package: com.example.consumer.vo
 * @className: UpdateOneBlogReqVo
 * @author: LiYinjian
 * @date: 2022/7/29 11:09
 * @version: 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOneBlogReqVo {
    @NotNull
    private Integer id;
    private Integer userId;
    private String title;
    private String content;
    private Date updateTime;
    private Integer type;
    private StatusEnum status;
}
