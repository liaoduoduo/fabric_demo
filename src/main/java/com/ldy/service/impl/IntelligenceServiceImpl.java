package com.ldy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldy.common.BaseContext;
import com.ldy.common.R;
import com.ldy.dto.IntelligencePageDto;
import com.ldy.entity.*;
import com.ldy.mapper.IntelligenceMapper;
import com.ldy.mapper.UserMapper;
import com.ldy.service.*;
import com.ldy.vo.IntelligenceVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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

    @Autowired
    private TokenLogService tokenLogService;

    @Autowired
    private IntelligenceMapper intelligenceMapper;

/*    @Override
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
    }*/

    @Override
    public Page<IntelligenceVo> pageQuery(IntelligencePageDto intelligencePageDto) {
        int page = intelligencePageDto.getPage();
        int pageSize = intelligencePageDto.getPageSize();
        Page<IntelligenceVo> intelligenceVoPage = intelligenceMapper.pageQuery(new Page<>(page, pageSize), intelligencePageDto);
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
    public R<String> buy(Long intelligenceId, String password) {
        //获取当前登录用户id
        Long payerId = BaseContext.getCurrentId();
        //获取该情报实体
        Intelligence intelligence = this.getById(intelligenceId);
        //获取当前登录用户实体
        User payer = userService.getById(payerId);

        //获取当前登录用户token钱包
        Long payerTokenId = payer.getTokenId();
        Token payerToken = tokenService.getById(payerTokenId);
        if (!password.equals(payerToken.getPassword())) {
            return R.error("密码错误！");
        }

        //判断该情报是否停售
        if (intelligence.getStatus() != 1) {
            return R.error("该情报已停售，无法购买！");
        }

        //判断该用户买的是不是自己发布的情报
        if (intelligence.getUserId().equals(payerId)) {
            return R.error("不能购买自己发布的情报！");
        }

        //判断该用户是不是已经买了该情报了
        LambdaQueryWrapper<UserIntelligence> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(UserIntelligence::getIntelligenceId, intelligence.getId())
                .eq(UserIntelligence::getToUserId, payerId);

        if (userIntelligenceService.count(queryWrapper) > 0) {
            return R.error("该情报你已拥有！");
        }

        //判断用户token余额
        BigDecimal price = intelligence.getToken();

        if (payerToken.getCurrentToken().compareTo(price) < 0) {
            return R.error("token余额不足，无法购买！");
        }
        //根据该情报价格，扣除token,并更新该付款用户余额
        payerToken.setCurrentToken(payerToken.getCurrentToken().subtract(price));
        tokenService.updateById(payerToken);

        //给收款用户更新余额
        Long payeeId = intelligence.getUserId();
        User payee = userService.getById(payeeId);
        Long payeeTokenId = payee.getTokenId();
        Token payeeToken = tokenService.getById(payeeTokenId);
        payeeToken.setCurrentToken(payeeToken.getCurrentToken().add(price));
        tokenService.updateById(payeeToken);

        //存到交易记录表
        TokenLog payerTokenLog = new TokenLog();
        payerTokenLog.setTokenId(payerTokenId);
        payerTokenLog.setCurrentChange(price.negate());//扣钱，取相反数
        payerTokenLog.setCurrentToken(payerToken.getCurrentToken());
        payerTokenLog.setBlockToken(payerToken.getBlockToken());
        payerTokenLog.setContent("购买情报");

        TokenLog payeeTokenLog = new TokenLog();
        payeeTokenLog.setTokenId(payeeTokenId);
        payeeTokenLog.setCurrentChange(price);
        payeeTokenLog.setCurrentToken(payeeToken.getCurrentToken());
        payeeTokenLog.setBlockToken(payeeToken.getBlockToken());
        payeeTokenLog.setContent("售出情报");

        tokenLogService.save(payerTokenLog);
        tokenLogService.save(payeeTokenLog);

        //并存到区块链（未完成）

        //更新我的情报表
        UserIntelligence userIntelligence = new UserIntelligence();
        userIntelligence.setFromUserId(intelligence.getUserId());
        userIntelligence.setToUserId(payerId);
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
