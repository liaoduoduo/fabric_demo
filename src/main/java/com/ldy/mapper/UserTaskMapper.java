package com.ldy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ldy.entity.UserTask;
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
public interface UserTaskMapper extends BaseMapper<UserTask> {
    /**
     * 用于查询协同任务下的研判任务是否已经被接单
     * 作用与协同任务的状态修改以及逻辑删除
     * @param list
     * @return
     */
    Integer selectUserTaskCountInCotasking(List<Long> list);
}
