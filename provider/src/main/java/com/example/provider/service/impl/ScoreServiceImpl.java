package com.example.provider.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.provider.bean.Score;
import com.example.provider.mapper.ScoreMapper;
import com.example.provider.service.ScoreService;
import org.springframework.stereotype.Service;

/**
 * @projectName: dubbo_demo
 * @package: com.example.provider.service.impl
 * @className: ScoreServiceImpl
 * @author: LiYinjian
 * @date: 2022/7/21 9:04
 * @version: 1.0
 */

@Service
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score> implements ScoreService {
}
