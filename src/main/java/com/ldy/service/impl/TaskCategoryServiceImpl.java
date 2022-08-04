package com.ldy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldy.entity.TaskCategory;
import com.ldy.mapper.TaskCategoryMapper;
import com.ldy.service.ITaskCategoryService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sunqing
 * @since 2022-07-24
 */
@Service
public class TaskCategoryServiceImpl extends ServiceImpl<TaskCategoryMapper, TaskCategory> implements ITaskCategoryService {

}
