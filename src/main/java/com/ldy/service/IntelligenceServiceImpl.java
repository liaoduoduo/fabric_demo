package com.ldy.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldy.mapper.IntelligenceMapper;
import com.ldy.pojo.Intelligence;
import org.springframework.stereotype.Service;

/**
 * @Author ldy
 * @Date 2022/7/1 17:20
 * @ClassName IntelligenceServiceImpl
 * @Description 情报业务实现类
 * @Version v1.0
 */
@Service
public class IntelligenceServiceImpl extends ServiceImpl<IntelligenceMapper, Intelligence> implements IntelligenceService{
}
