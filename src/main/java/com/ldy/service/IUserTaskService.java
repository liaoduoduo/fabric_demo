package com.ldy.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ldy.common.R;
import com.ldy.entity.UserTask;
import com.ldy.vo.TaskVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sunqing
 * @since 2022-07-24
 */
public interface IUserTaskService extends IService<UserTask> {

    Page<TaskVo> getUserTaskByUserId(Page<TaskVo> taskVoPage, String name, Long userId);

    R<String> saveUserTask(Long id);
}
