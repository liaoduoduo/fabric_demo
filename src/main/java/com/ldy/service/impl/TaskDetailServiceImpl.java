package com.ldy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldy.entity.TaskDetail;
import com.ldy.mapper.TaskDetailMapper;
import com.ldy.service.ITaskDetailService;
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
public class TaskDetailServiceImpl extends ServiceImpl<TaskDetailMapper, TaskDetail> implements ITaskDetailService {

}
