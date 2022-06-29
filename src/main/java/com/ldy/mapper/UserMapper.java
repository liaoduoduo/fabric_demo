package com.ldy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ldy.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author ldy
 * @Date 2022/6/25 15:28
 * @ClassName UserMapper
 * @Description TODO
 * @Version v1.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
