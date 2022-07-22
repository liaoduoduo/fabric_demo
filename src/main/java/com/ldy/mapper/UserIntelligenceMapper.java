package com.ldy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldy.entity.UserIntelligence;
import com.ldy.vo.IntelligenceVo;
import com.ldy.vo.UserIntelligenceVo;
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
    Page<UserIntelligenceVo> listUserIntelligence(Page<UserIntelligenceVo> page, Long userId, String name);
}
