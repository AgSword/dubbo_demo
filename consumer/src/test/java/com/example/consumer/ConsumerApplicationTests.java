package com.example.consumer;

import com.example.api.enums.StatusEnum;
import com.example.consumer.controller.BlogController;
import com.example.consumer.vo.InsetOneBlogReqVo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class ConsumerApplicationTests {

    @Autowired
    BlogController blogController;

    @Test
    void insertBlog() {
        InsetOneBlogReqVo reqVo = new InsetOneBlogReqVo();
        reqVo.setUserId(1);
        reqVo.setTitle("java的特性");
        reqVo.setContent("java的特性");
        reqVo.setCreateTime(new Date());
        reqVo.setUpdateTime(new Date());
        reqVo.setType(1);
        reqVo.setStatus(StatusEnum.getStatusEnum(1));
        blogController.insertOne(reqVo);
    }

    @Test
    void testMulInsert() {
        System.out.println(blogController.test());
    }


}
