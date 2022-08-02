package com.example.provider.mapper;

import com.example.api.dto.CountAboutBlogDTO;
import com.example.api.entity.Blog;
import com.example.api.dto.BlogCountDTO;
import com.example.api.dto.TypeCountDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BlogMapper {
    /**
     * 插入一个blog，数据表中id字段为自增
     *
     * @param blog
     * @return
     */
    @Insert("insert into t_blog (user_id,title,content,create_time,update_time,type,status) " +
            "value (#{userId},#{title},#{content},#{createTime},#{updateTime},#{type},#{status})")
    int insert(Blog blog);

    @Select("select * from t_blog where id = #{id}")
    Blog selectById(Integer id);

    @Delete("delete from t_blog where id = #{id}")
    int deleteById(Integer id);

    @Update("update t_blog set user_id = #{userId}," +
            "title = #{title},content = #{content}," +
            "create_time = #{createTime},update_time = #{updateTime}," +
            "type = #{type},status = #{status} where id = #{id}")
    int updateById(Blog blog);

    @Select("select * from t_blog order by update_time desc")
    List<Blog> selectAll();


    Integer mulInsert(List<Blog> list);

    List<TypeCountDTO> countAboutType();

    List<BlogCountDTO> countAboutBlog(CountAboutBlogDTO countAboutBlogDTO);
}
