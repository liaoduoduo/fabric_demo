package com.ldy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldy.common.BaseContext;
import com.ldy.common.R;
import com.ldy.entity.Token;
import com.ldy.entity.User;
import com.ldy.entity.UserIntelligence;
import com.ldy.mapper.IntelligenceMapper;
import com.ldy.entity.Intelligence;
import com.ldy.service.*;
import com.ldy.vo.IntelligenceVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ldy
 * @Date 2022/7/1 17:20
 * @ClassName IntelligenceServiceImpl
 * @Description 情报业务实现类
 * @Version v1.0
 */
@Service
public class IntelligenceServiceImpl extends ServiceImpl<IntelligenceMapper, Intelligence> implements IntelligenceService {

    @Autowired
    private UserIntelligenceService userIntelligenceService;

    @Autowired
    private UserService userService;

    @Autowired
    private IntelligenceCategoryService intelligenceCategoryService;

    @Autowired
    private TokenService tokenService;

    @Override
    public Page<IntelligenceVo> pageQuery(int page, int pageSize, String name) {
        Page<Intelligence> intelligencePage = new Page<>(page, pageSize);
        Page<IntelligenceVo> intelligenceVoPage = new Page<>();
        LambdaQueryWrapper<Intelligence> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name != null, Intelligence::getName, name).orderByDesc(Intelligence::getUpdateTime);
        this.page(intelligencePage, queryWrapper);
        //对象拷贝
        BeanUtils.copyProperties(intelligencePage, intelligenceVoPage, "records");
        List<Intelligence> records = intelligencePage.getRecords();
        List<IntelligenceVo> list = new ArrayList<>();
        for (Intelligence record : records) {
            IntelligenceVo intelligenceDto = new IntelligenceVo();
            BeanUtils.copyProperties(record, intelligenceDto);
            Long categoryId = record.getIntelligenceCategoryId();
            if (categoryId != null) {
                intelligenceDto.setCategoryName(intelligenceCategoryService.getById(categoryId).getName());
            }
            Long userId = record.getUserId();
            if (userId != null) {
                User user = userService.getById(userId);
                intelligenceDto.setUserName(user.getName());
            }

            list.add(intelligenceDto);

        }
        intelligenceVoPage.setRecords(list);
        return intelligenceVoPage;
    }

    @Override
    public void batchStatus(Integer status, List<Long> ids) {
        for (Long id : ids) {
            Intelligence intelligence = this.getById(id);
            intelligence.setStatus(status);
            this.updateById(intelligence);
        }
    }

    @Override
    @Transactional
    public void saveByUserIntelligence(Intelligence intelligence) {
        Long userId = BaseContext.getCurrentId();
        intelligence.setUserId(userId);
        this.save(intelligence);

        UserIntelligence userIntelligence = new UserIntelligence();
        userIntelligence.setIntelligenceId(intelligence.getId());
        userIntelligence.setFromUserId(intelligence.getUserId());
        userIntelligenceService.save(userIntelligence);
    }

    @Override
    @Transactional
    public R<String> buy(Long intelligenceId) {
        //获取当前登录用户id
        Long userId = BaseContext.getCurrentId();
        //获取该情报实体
        Intelligence intelligence = this.getById(intelligenceId);
        //获取当前登录用户实体
        User user = userService.getById(userId);

        //判断该情报是否停售
        if (intelligence.getStatus() != 1) {
            return R.error("该情报已停售，无法购买！");
        }

        //判断该用户买的是不是自己发布的情报
        if (intelligence.getUserId().equals(userId)) {
            return R.error("不能购买自己发布的情报！");
        }

        //判断该用户是不是已经买了该情报了
        LambdaQueryWrapper<UserIntelligence> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(UserIntelligence::getIntelligenceId, intelligence.getId())
                .eq(UserIntelligence::getToUserId, userId);

        if (userIntelligenceService.count(queryWrapper) > 0) {
            return R.error("该情报你已拥有！");
        }

        //判断用户token余额
        Token token = tokenService.getById(user.getTokenId());
        if (token.getCurrentToken().compareTo(intelligence.getToken()) < 0) {
            return R.error("token余额不足，无法购买！");
        }
        //根据该情报价格，扣除token,并更新该用户余额
        token.setCurrentToken(token.getCurrentToken().subtract(intelligence.getToken()));
        tokenService.updateById(token);

        //并存到区块链（未完成）

        //更新我的情报表
        UserIntelligence userIntelligence = new UserIntelligence();
        userIntelligence.setFromUserId(intelligence.getUserId());
        userIntelligence.setToUserId(userId);
        userIntelligence.setIntelligenceId(intelligenceId);
        userIntelligenceService.save(userIntelligence);
        return R.success("购买成功！");
    }

    @Override
    public R<String> deleteBatch(List<Long> ids) {
        List<Intelligence> intelligences = this.listByIds(ids);
        for (Intelligence intelligence : intelligences) {
            if (intelligence.getStatus() != 0) {
                return R.error("所选含有未停用的情报，无法删除！");
            }
        }
        this.removeByIds(ids);
        return R.success("删除成功！");
    }
}
