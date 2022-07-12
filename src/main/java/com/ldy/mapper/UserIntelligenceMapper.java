package com.ldy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ldy.entity.UserIntelligence;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author ldy
 * @Date 2022/7/11 16:29
 * @ClassName UserIntelligence
 * @Description TODO
 * @Version v1.0
 */
@Mapper
public interface UserIntelligenceMapper extends BaseMapper<UserIntelligence> {
    UserIntelligence queryLatestUserIntelligence(Long userId);
}
