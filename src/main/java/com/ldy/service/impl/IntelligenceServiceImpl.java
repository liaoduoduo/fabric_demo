package com.ldy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldy.mapper.IntelligenceMapper;
import com.ldy.entity.Intelligence;
import com.ldy.service.IntelligenceService;
import org.springframework.stereotype.Service;

/**
 * @Author ldy
 * @Date 2022/7/1 17:20
 * @ClassName IntelligenceServiceImpl
 * @Description 情报业务实现类
 * @Version v1.0
 */
@Service
public class IntelligenceServiceImpl extends ServiceImpl<IntelligenceMapper, Intelligence> implements IntelligenceService {
}
