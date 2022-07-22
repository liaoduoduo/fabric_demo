package com.ldy.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldy.common.BaseContext;
import com.ldy.entity.Category;
import com.ldy.entity.User;
import com.ldy.mapper.UserIntelligenceMapper;
import com.ldy.entity.UserIntelligence;
import com.ldy.service.CategoryService;
import com.ldy.service.UserIntelligenceService;
import com.ldy.service.UserService;
import com.ldy.vo.UserIntelligenceVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @Override
    public Page<UserIntelligenceVo> pageQuery(int page, int pageSize, String name) {

        Page<UserIntelligenceVo> userIntelligenceVoPage = new Page<>(page, pageSize);

        Long userId = BaseContext.getCurrentId();
        Page<UserIntelligenceVo> userIntelligenceVoResultPage = userIntelligenceMapper.listUserIntelligence(userIntelligenceVoPage, userId, name);

        List<UserIntelligenceVo> records = userIntelligenceVoResultPage.getRecords();

        for (UserIntelligenceVo record : records) {
            Long formUserId = record.getFormUserId();
            if (formUserId.equals(userId)) {
                record.setOrigin("本人发布");
            } else {
                record.setOrigin("购买他人");
            }

            User formUser = userService.getById(formUserId);
            String formUserName = formUser.getName();
            record.setFromUserName(formUserName);

            Category category = categoryService.getById(record.getCategoryId());
            record.setCategoryName(category.getName());

        }
        return userIntelligenceVoResultPage;
    }
}
