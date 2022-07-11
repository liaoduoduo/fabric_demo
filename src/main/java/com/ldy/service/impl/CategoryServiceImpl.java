package com.ldy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldy.mapper.CategoryMapper;
import com.ldy.entity.Category;
import com.ldy.service.CategoryService;
import org.springframework.stereotype.Service;

/**
 * @Author ldy
 * @Date 2022/7/4 16:06
 * @ClassName CategoryServiceImpl
 * @Description TODO
 * @Version v1.0
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
}
