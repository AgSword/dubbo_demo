package com.example.consumer.vo;

import com.example.api.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Convert;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @projectName: dubbo_demo
 * @package: com.example.consumer.vo
 * @className: InsetOneBlogReqVo
 * @author: LiYinjian
 * @date: 2022/7/28 17:44
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsetOneBlogReqVo {
    @NotNull
    private Integer userId;
    @NotNull
    @NotEmpty
    @Length(max = 24)
    private String title;
    private String content;
    private Date createTime;
    private Date updateTime;
    @NotNull
    private Integer type;
    @NotNull
    private StatusEnum status;
}
