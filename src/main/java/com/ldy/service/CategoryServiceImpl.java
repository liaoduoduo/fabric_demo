package com.ldy.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldy.mapper.CategoryMapper;
import com.ldy.pojo.Category;
import org.springframework.stereotype.Service;

/**
 * @Author ldy
 * @Date 2022/7/4 16:06
 * @ClassName CategoryServiceImpl
 * @Description TODO
 * @Version v1.0
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService{
}
