package com.example.provider.service;

import com.example.api.api.BlogService;
import com.example.api.dto.CountAboutBlogDTO;
import com.example.api.dto.Response;
import com.example.api.entity.Blog;
import com.example.api.enums.RespStatus;
import com.example.api.enums.StatusEnum;
import com.example.provider.mapper.BlogMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @projectName: dubbo_demo
 * @package: com.example.provider.service
 * @className: BlogServiceImpl
 * @author: LiYinjian
 * @date: 2022/7/28 11:49
 * @version: 1.0
 */

@Component
@DubboService
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogMapper blogMapper;

    /**
     * 根据id插入blog
     *
     * @param blog
     * @return
     */
    @Override
    public Response insertBlog(Blog blog) {
        if (blog == null) {
            return Response.fail(RespStatus.INSERT_ID_NULL.getStatus(), RespStatus.INSERT_ID_NULL.getMsg());
        }
        int influenced = blogMapper.insert(blog);
        if (influenced != 1) {
            return Response.fail(RespStatus.SINGLE_INSERT_DATABASE_FAIL.getStatus(), RespStatus.SINGLE_INSERT_DATABASE_FAIL.getMsg());
        }
        return Response.ok();
    }

    /**
     * 查询出所有的blogs,按update_time降序排列
     *
     * @return
     */
    @Override
    public Response selectAllBlogs() {
        return Response.ok(blogMapper.selectAll());
    }

    @Override
    public Response fileImport(MultipartFile file) {
        //传入的文件的文件名规范为   title_userId_type_published.后缀名
        String fileName = file.getOriginalFilename().split("\\.")[0];
        String[] s = fileName.split("_");
        if (s.length != 4) {
            return Response.fail(RespStatus.FILENAME_ERROR.getStatus(), RespStatus.FILENAME_ERROR.getMsg());
        }
        Blog blog = new Blog();
        try {
            //如果文件名不符合规范
            blog.setTitle(s[0]);
            blog.setUserId(Integer.parseInt(s[1]));
            blog.setType(Integer.parseInt(s[2]));
            blog.setStatus(StatusEnum.getStatusEnum(Integer.parseInt(s[3])));
        } catch (Exception e) {
            return Response.fail(RespStatus.FILENAME_ERROR.getStatus(), RespStatus.FILENAME_ERROR.getMsg());
        }
        //获取blog的content
        StringBuilder content = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            while (reader.ready()) {
                content.append((char) reader.read());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return Response.fail(RespStatus.FILE_IMPORT_ERROR.getStatus(), RespStatus.FILE_IMPORT_ERROR.getMsg());
        }
        if (!StringUtils.hasLength(content.toString())) {
            return Response.fail(RespStatus.FILE_IS_NULL.getStatus(), RespStatus.FILE_IS_NULL.getMsg());
        }
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setContent(content.toString());
        return Response.ok(blogMapper.insert(blog));
    }

    /**
     * 从excel中获取多条记录，插入数据库中
     *
     * @param file
     * @return
     */
    @Override
    public Response excelImport(MultipartFile file) {
        List<Blog> list = new ArrayList<>();
        ;
        Workbook workbook = null;
        String filename = file.getOriginalFilename();
        try {
            if (filename != null) {
                // excel有两种，xls和xlsx
                if (filename.endsWith("xls")) {
                    workbook = new HSSFWorkbook(file.getInputStream());
                } else if (filename.endsWith("xlsx")) {
                    workbook = new XSSFWorkbook(file.getInputStream());
                } else {
                    return Response.fail(RespStatus.IS_NOT_EXCEL.getStatus(), RespStatus.IS_NOT_EXCEL.getMsg());
                }
                //excel的工作簿默认为Sheet1
                Sheet sheet = workbook.getSheet("Sheet1");
                int lastRowNum = sheet.getLastRowNum();
                //excel中row数总共为0，则说明excel表格为空
                if (lastRowNum == 0) {
                    return Response.fail(RespStatus.EXCEL_IS_NULL.getStatus(), RespStatus.EXCEL_IS_NULL.getMsg());
                }
                for (int i = 1; i <= lastRowNum; i++) {
                    Row row = sheet.getRow(i);
                    if (row != null) {
                        list.add(getBlogFromRow(row));
                    }
                }
                Integer influenced = blogMapper.mulInsert(list);
                if (influenced != list.size()) {
                    return Response.fail(RespStatus.MUL_INSERT_DATABASE_FAIL.getStatus(), RespStatus.MUL_INSERT_DATABASE_FAIL.getMsg());
                }
            } else {
                return Response.fail(RespStatus.EXCEL_IS_EXIST.getStatus(), RespStatus.EXCEL_IS_EXIST.getMsg());
            }
        } catch (IOException e) {
            return Response.fail(500, e.getMessage());
        }

        return null;
    }


    /**
     * excel规范
     * 第一列|第二列  |第三列 |第四列  |第五列|第六列    |创建时间   |更新时间
     * 序号  |userId|title |content|type |published|createTime|updateTime
     * 从excel每一行中提取数据，封装到blog中
     *
     * @param row
     * @return
     */
    private Blog getBlogFromRow(Row row) {
        Blog blog = new Blog();
        //因为数据库中id为自增，且excel中的第一列序号与id无关，因此excel第一列不读取
        // 读取userId
        blog.setUserId((int) row.getCell(1).getNumericCellValue());
        // 读取title
        blog.setTitle(row.getCell(2).getStringCellValue());
        // 读取content
        blog.setContent(row.getCell(3).getStringCellValue());
        // 读取type
        blog.setType((int) row.getCell(4).getNumericCellValue());
        // 读取published
        blog.setStatus(StatusEnum.getStatusEnum((int) row.getCell(5).getNumericCellValue()));
        //读取createTime
        blog.setCreateTime(row.getCell(6).getDateCellValue());
        //读取updateTime
        blog.setUpdateTime(row.getCell(7).getDateCellValue());
        return blog;
    }

    /**
     * 根据id查询blog
     *
     * @param id
     * @return
     */
    @Override
    public Response selectBlogById(Integer id) {
        if (id == null) {
            return Response.fail(RespStatus.SELECT_ID_IS_NULL.getStatus(), RespStatus.SELECT_ID_IS_NULL.getMsg());
        }
        Blog blog = blogMapper.selectById(id);
        if (blog == null) {
            return Response.fail(RespStatus.SELECT_IS_NULL.getStatus(), RespStatus.SELECT_IS_NULL.getMsg());
        }
        return Response.ok(blog);
    }

    @Override
    public Response getBlogList(List<Integer> ids) {
        return null;
    }

    /**
     * 根据blog的id更新blog
     * 由于在前端只输入t_blog中想要更新的字段，所以需要先根据id查找数据库中原先的记录行信息，然后采用反射的方法将前端传入的数据赋值到查询到的数据中
     *
     * @param newBlog
     * @return
     */
    @Override
    public Response updateBlogById(Blog newBlog) {
        Blog oldBlog = blogMapper.selectById(newBlog.getId());  // 数据库里原来的对象
        Field[] fields = Blog.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String name = field.getName();
            Method method = null;
            try {
                method = Blog.class.getMethod("get" + name.substring(0, 1).toUpperCase() + name.substring(1));
                Object o = method.invoke(newBlog);  // 新的对象的属性不为空，那么就要复制到老对象中
                if (o != null) {
                    Object para = null;
                    switch (name) {
                        case "id":
                            para = Integer.class;
                            break;
                        case "userId":
                            para = Integer.class;
                            break;
                        case "type":
                            para = Integer.class;
                            break;
                        case "updateTime":
                            para = Date.class;
                            break;
                        case "title":
                            para = String.class;
                            break;
                        case "content":
                            para = String.class;
                            break;
                        case "published":
                            para = Boolean.class;
                            break;

                    }
                    Method setter = Blog.class.getMethod("set" + name.substring(0, 1).toUpperCase() + name.substring(1), (Class<?>) para);
                    setter.invoke(oldBlog, o);
                }
            } catch (NoSuchMethodException e) {
                return Response.fail(500, e.getMessage());
            } catch (InvocationTargetException e) {
                return Response.fail(500, e.getMessage());
            } catch (IllegalAccessException e) {
                return Response.fail(500, e.getMessage());
            }
        }
        int influenced = blogMapper.updateById(oldBlog);
        if (influenced != 1) {
            return Response.fail(RespStatus.UPDATE_DATABASE_FAIL.getStatus(), RespStatus.UPDATE_DATABASE_FAIL.getMsg());
        }
        return Response.ok();
    }

    @Override
    public Response updateBlogs(List<Integer> ids) {
        return null;
    }

    @Override
    public Response deleteBlogById(Integer id) {
        if (id == null) {
            return Response.fail(RespStatus.DELETE_ID_IS_NULL.getStatus(), RespStatus.DELETE_ID_IS_NULL.getMsg());
        }
        int influenced = blogMapper.deleteById(id);
        if (influenced != 1) {
            return Response.fail(RespStatus.DELETE_DATABASE_FAIL.getStatus(), RespStatus.DELETE_DATABASE_FAIL.getMsg());
        }
        return Response.ok();
    }

    @Override
    public Integer test(List<Blog> list) {
        return blogMapper.mulInsert(list);
    }

    @Override
    public Response countAboutType() {
        return Response.ok(blogMapper.countAboutType());
    }

    @Override
    public Response countAboutBlog(CountAboutBlogDTO countAboutBlogDTO) {
        if (countAboutBlogDTO == null) {
            return Response.fail(RespStatus.COUNT_PARA_IS_NULL.getStatus(), RespStatus.COUNT_PARA_IS_NULL.getMsg());
        }
        return Response.ok(blogMapper.countAboutBlog(countAboutBlogDTO));
    }

}