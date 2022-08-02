package com.example.api.api;


import com.example.api.dto.BlogCountDTO;
import com.example.api.dto.CountAboutBlogDTO;
import com.example.api.dto.TypeCountDTO;
import com.example.api.entity.Blog;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface BlogService {
    Integer insertBlog(Blog blog);

    Blog selectBlogById(Integer id);

    Integer updateBlogById(Blog blog) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

    Integer deleteBlogById(Integer id);

    List<Blog> selectAllBlogs();

    Integer fileImport(MultipartFile file);

    void excelImport(MultipartFile file);

    Integer test(List<Blog> list);

    List<TypeCountDTO> countAboutType();

    List<BlogCountDTO> countAboutBlog(CountAboutBlogDTO countAboutBlogDTO);
}
