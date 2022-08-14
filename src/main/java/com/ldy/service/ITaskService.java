package com.ldy.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ldy.common.R;
import com.ldy.dto.TaskDto;
import com.ldy.entity.Task;
import com.ldy.vo.TaskVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sunqing
 * @since 2022-07-24
 */
public interface ITaskService extends IService<Task> {

    R<String> saveTaskAndBlockToken(Task task);

    List<TaskVo> getTaskDetailByCotaskId(Long id);

    R<String> updateToken(TaskDto taskDto);

    R<String> removeTaskByIds(Long[] ids);

    Page<TaskVo> getAllTaskInfoWithUserPage(Page<TaskVo> taskPage, String name, Long userId);

    R<String> tokenCheckOut(Long id);
}
