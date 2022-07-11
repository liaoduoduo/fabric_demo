package com.ldy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ldy.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author ldy
 * @Date 2022/6/25 15:28
 * @ClassName UserMapper
 * @Description 用户数据访问映射
 * @Version v1.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
