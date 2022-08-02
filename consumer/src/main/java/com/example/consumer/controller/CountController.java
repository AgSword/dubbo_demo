package com.example.consumer.controller;

import com.example.api.api.BlogService;
import com.example.api.dto.CountAboutBlogDTO;
import com.example.api.dto.Response;
import com.example.consumer.utils.BeanMapper;
import com.example.consumer.vo.CountAboutBlogVo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description
 * @projectName: dubbo_demo
 * @package: com.example.consumer.controller
 * @className: CountController
 * @author: LiYinjian
 * @date: 2022/8/1 13:40
 * @version: 1.0
 */

@RestController
@RequestMapping(value = "/count")
public class CountController {

    @Autowired
    BeanMapper beanMapper;

    @DubboReference
    BlogService blogService;

    /**
     * 关于blog的type的统计，返回每种type的blog的数量,可以在前端形成 blog数关于type的饼图
     *
     * @return
     */
    @GetMapping(value = "/type")
    public Response countAboutType() {
        return blogService.countAboutType();
    }

    /**
     * 根据userId,type,published,createTime，updateTime这五个条件，按月查询出符合这些条件的blog数，可以在前端形成折线图或者条状图
     *
     * @return
     */
    @GetMapping(value = "/blog")
    public Response countAboutBlog(@RequestBody CountAboutBlogVo countAboutBlogVo) {
        CountAboutBlogDTO countAboutBlogDTO = beanMapper.convert(countAboutBlogVo, CountAboutBlogDTO.class);
        return blogService.countAboutBlog(countAboutBlogDTO);
    }
}
