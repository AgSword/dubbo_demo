package com.example.provider;

import com.example.api.entity.Blog;
import com.example.provider.mapper.BlogMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class ProviderApplicationTests {

    @Autowired
    BlogMapper blogMapper;

    @Test
    void contextLoads() {
    }



}
