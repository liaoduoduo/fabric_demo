package com.ldy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldy.mapper.CategoryMapper;
import com.ldy.entity.IntelligenceCategory;
import com.ldy.service.IntelligenceCategoryService;
import org.springframework.stereotype.Service;

/**
 * @Author ldy
 * @Date 2022/7/4 16:06
 * @ClassName CategoryServiceImpl
 * @Description TODO
 * @Version v1.0
 */
@Service
public class IntelligenceCategoryServiceImpl extends ServiceImpl<CategoryMapper, IntelligenceCategory> implements IntelligenceCategoryService {
}
