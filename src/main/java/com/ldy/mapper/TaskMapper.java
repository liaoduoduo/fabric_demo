package com.ldy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ldy.entity.Task;
import com.ldy.vo.TaskVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sunqing
 * @since 2022-07-24
 */
@Mapper
public interface TaskMapper extends BaseMapper<Task> {

    List<TaskVo> getTaskWithCategoryByCotaskId(Long id);
}
