package com.ldy.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ldy.common.BaseContext;
import com.ldy.common.R;
import com.ldy.entity.TokenDeal;
import com.ldy.service.ITokenDealService;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sunqing
 * @since 2022-08-03
 */
@RestController
@RequestMapping("/token-deal")
public class TokenDealController {

    @Autowired
    ITokenDealService tokenDealService;

    @ApiModelProperty("查询个人账单")
    @GetMapping("/getpage")
    public R<Page<TokenDeal>> getpage(@RequestParam(value = "page", defaultValue = "1") int page,
                                      @RequestParam(value = "pageSize", defaultValue = "5")int pageSize){
        Page<TokenDeal> tokenDealPage = new Page<>(page, pageSize);
        LambdaQueryWrapper<TokenDeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TokenDeal::getFromUser, BaseContext.getCurrentId()).or().eq(TokenDeal::getToUser,BaseContext.getCurrentId());
        tokenDealPage = tokenDealService.page(tokenDealPage, queryWrapper);
        return R.success(tokenDealPage);
    }
}

