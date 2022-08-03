package com.example.consumer.vo;

import com.example.api.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

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
public class InsertOneBlogReqVo {
    @NotNull(message = "用户id不能为空")
    private Integer userId;
    @NotNull(message = "标题不能为空")
    @NotEmpty(message = "标题不能为空字符串")
    @Length(max = 24,message = "标题长度不能超过24")
    private String title;
    private String content;
    private Date createTime;
    private Date updateTime;
    @NotNull(message = "类别不能为空")
    private Integer type;
    @NotNull(message = "状态不能为空")
    private StatusEnum status;
}
