package com.ldy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldy.entity.Task;
import com.ldy.entity.User;
import com.ldy.vo.TaskVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;


/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author sunqing
 * @since 2022-07-24
 */
@Mapper
public interface TaskMapper extends BaseMapper<Task> {

    List<TaskVo> getTaskDetailByCotaskId(Long id);

    Page<TaskVo> getAllTaskInfoWithUserPage(Page<TaskVo> taskPage, @Param("name") String name, @Param("unit") String unit);
}
