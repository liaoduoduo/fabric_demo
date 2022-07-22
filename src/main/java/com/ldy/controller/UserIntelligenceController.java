package com.ldy.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldy.common.R;
import com.ldy.service.UserIntelligenceService;
import com.ldy.vo.UserIntelligenceVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author ldy
 * @Date 2022/7/12 16:58
 * @ClassName UserIntelligenceController
 * @Description 我的情报 控制器
 * @Version v1.0
 */
@RestController
@RequestMapping("/userIntelligence")
public class UserIntelligenceController {

    @Autowired
    private UserIntelligenceService userIntelligenceService;

    /**
     * @param page
     * @param pageSize
     * @param name
     * @return com.ldy.common.R<com.baomidou.mybatisplus.extension.plugins.pagination.Page < com.ldy.vo.IntelligenceVo>>
     * @description 分页查询
     * @date 2022/7/22 14:23
     */
    @GetMapping("/page")
    public R<Page<UserIntelligenceVo>> page(int page, int pageSize, String name) {
        return R.success(userIntelligenceService.pageQuery(page, pageSize, name));
    }

}
