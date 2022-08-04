package com.ldy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldy.entity.Task;
import com.ldy.mapper.TaskMapper;
import com.ldy.service.ITaskService;
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
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements ITaskService {

}
