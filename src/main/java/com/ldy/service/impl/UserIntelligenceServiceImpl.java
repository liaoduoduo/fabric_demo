package com.ldy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldy.mapper.UserIntelligenceMapper;
import com.ldy.entity.UserIntelligence;
import com.ldy.service.UserIntelligenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author ldy
 * @Date 2022/7/11 16:31
 * @ClassName UserIntelligenceImpl
 * @Description TODO
 * @Version v1.0
 */
@Service
public class UserIntelligenceServiceImpl extends ServiceImpl<UserIntelligenceMapper, UserIntelligence> implements UserIntelligenceService {

    @Autowired
    private UserIntelligenceMapper userIntelligenceMapper;

    @Override
    public UserIntelligence queryLatestUserIntelligence(Long userId) {
        return userIntelligenceMapper.queryLatestUserIntelligence(userId);
    }
}
