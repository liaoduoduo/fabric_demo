package com.ldy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ldy.pojo.Task;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author ldy
 * @Date 2022/7/1 17:23
 * @ClassName TaskMapper
 * @Description 研判任务数据访问映射
 * @Version v1.0
 */
@Mapper
public interface TaskMapper extends BaseMapper<Task> {
}
