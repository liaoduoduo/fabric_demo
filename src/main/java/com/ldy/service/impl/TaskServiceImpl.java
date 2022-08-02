package com.ldy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldy.mapper.TaskMapper;
import com.ldy.entity.Task;
import com.ldy.service.TaskService;
import org.springframework.stereotype.Service;

/**
 * @Author ldy
 * @Date 2022/7/1 17:28
 * @ClassName TaskServiceImpl
 * @Description 研判任务业务实现类
 * @Version v1.0
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {
}