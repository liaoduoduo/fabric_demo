package com.ldy.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ldy.entity.UserIntelligence;
import com.ldy.vo.UserIntelligenceVo;

/**
 * @Author ldy
 * @Date 2022/7/11 16:30
 * @ClassName UserIntelligenceService
 * @Description TODO
 * @Version v1.0
 */
public interface UserIntelligenceService extends IService<UserIntelligence> {
    Page<UserIntelligenceVo> pageQuery(int page, int pageSize, String name);

}
