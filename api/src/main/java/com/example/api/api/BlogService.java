package com.example.api.api;


import com.example.api.dto.CountAboutBlogDTO;
import com.example.api.dto.Response;
import com.example.api.entity.Blog;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BlogService {
    Response insertBlog(Blog blog);

    Response selectBlogById(Integer id);

    Response getBlogList(List<Integer> ids);

    Response updateBlogById(Blog blog);

    Response updateBlogs(List<Integer> ids);

    Response deleteBlogById(Integer id);

    Response selectAllBlogs();

    Response fileImport(MultipartFile file);

    Response excelImport(MultipartFile file);

    Integer test(List<Blog> list);

    Response countAboutType();

    Response countAboutBlog(CountAboutBlogDTO countAboutBlogDTO);
}
