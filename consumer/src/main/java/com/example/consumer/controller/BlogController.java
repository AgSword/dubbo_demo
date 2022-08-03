package com.example.consumer.controller;

import com.example.api.api.BlogService;
import com.example.api.enums.RespStatus;
import com.example.consumer.vo.Response;
import com.example.api.entity.Blog;
import com.example.consumer.utils.BeanMapper;
import com.example.consumer.vo.InsertOneBlogReqVo;
import com.example.consumer.vo.UpdateOneBlogReqVo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @projectName: dubbo_demo
 * @package: com.example.consumer.controller
 * @className: BlogController
 * @author: LiYinjian
 * @date: 2022/7/28 14:17
 * @version: 1.0
 */

@RestController
@RequestMapping("/blog")
public class BlogController {

    @DubboReference
    BlogService blogService;

    @Autowired
    BeanMapper beanMapper;

    /**
     * 根据id查询blog
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Response selectById(Integer id) {
        if (id == null) {
            return Response.fail(RespStatus.SELECT_ID_IS_NULL.getStatus(), RespStatus.SELECT_ID_IS_NULL.getMsg());
        }
        Blog blog = blogService.selectBlogById(id);
        if (blog == null) {
            return Response.fail(RespStatus.SELECT_IS_NULL.getStatus(), RespStatus.SELECT_IS_NULL.getMsg());
        }
        return Response.ok(blog);
    }

    /**
     * 查询出所有的blog
     *
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Response selectAllBlogs() {
        return Response.ok(blogService.selectAllBlogs());
    }

    /**
     * 向t_blog表中插入一条记录，向将前端输入数据vo对象映射到entity对象（Blog对象），原因是表中的主键id为自增
     * 再将Blog对象插入到表中
     *
     * @param blogReqVo
     * @return
     */
    @PostMapping(value = "/")
    public Response insertOne(@RequestBody @Validated InsertOneBlogReqVo blogReqVo) {
        Blog blog = beanMapper.convert(blogReqVo, Blog.class);
        if (blog == null) {
            return Response.fail(RespStatus.INSERT_ID_NULL.getStatus(), RespStatus.INSERT_ID_NULL.getMsg());
        }
        Integer influence = blogService.insertBlog(blog);
        if (influence != 1) {
            return Response.fail(RespStatus.SINGLE_INSERT_DATABASE_FAIL.getStatus(), RespStatus.SINGLE_INSERT_DATABASE_FAIL.getMsg());
        }
        return Response.ok();
    }

    /**
     * 通过文件导入插入单条记录，文件名为title_userId_type_published.文件后缀名，文件内容为blog中的content
     *
     * @param file
     * @return
     */
    @PostMapping(value = "/fileImport")
    public Response fileImport(MultipartFile file) {
        if (blogService.fileImport(file) != 1) {
            return Response.fail(RespStatus.FILE_IMPORT_ERROR.getStatus(), RespStatus.FILE_IMPORT_ERROR.getMsg());
        }
        return Response.ok();
    }

    @PostMapping(value = "excelImport")
    public Response excelImport(MultipartFile file) {
        blogService.excelImport(file);
        return Response.ok();
    }

    /**
     * 根据id删除blog
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public Response deleteById(Integer id) {
        if (id == null) {
            return Response.fail(RespStatus.DELETE_ID_IS_NULL.getStatus(), RespStatus.DELETE_ID_IS_NULL.getMsg());
        }
        if (blogService.deleteBlogById(id) != 1) {
            return Response.fail(RespStatus.DELETE_DATABASE_FAIL.getStatus(), RespStatus.DELETE_DATABASE_FAIL.getMsg());
        }
        return Response.ok();
    }

    /**
     * 根据id修改blog
     *
     * @param reqVo
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public Response updateOne(@RequestBody @Validated UpdateOneBlogReqVo reqVo) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        Blog blog = beanMapper.convert(reqVo, Blog.class);
        if (blogService.updateBlogById(blog) != 1) {
            return Response.fail(RespStatus.UPDATE_DATABASE_FAIL.getStatus(), RespStatus.UPDATE_DATABASE_FAIL.getMsg());
        }
        return Response.ok();
    }


    /**
     * 测试，与业务功能无关
     *
     * @return
     */
    @RequestMapping(value = "/test")
    public Response test() {
        Blog blog = new Blog();
        blog.setUserId(1);
        blog.setTitle("java是啥");
        blog.setContent("java是啥");
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setType(1);
        List<Blog> list = new ArrayList<>();
        list.add(blog);
        list.add(blog);
        Integer integer = blogService.test(list);
        System.out.println(integer);
        return Response.ok(integer);
    }

}
