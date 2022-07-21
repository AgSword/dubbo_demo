package com.example.provider.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

/**
 * @projectName: dubbo_demo
 * @package: com.example.provider.dao
 * @className: Score
 * @author: LiYinjian
 * @date: 2022/7/20 17:42
 * @version: 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_score")

public class Score implements Serializable {
    private String username;
    private Integer score;

}
