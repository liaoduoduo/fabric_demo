package com.ldy.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author ldy
 * @Description token日志表
 * @Version v1.0
 * @Date 2022/8/3 14:41
 */
@Data
public class TokenLog implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long tokenId;

    //本次记录非冻结金额变更数目
    private BigDecimal currentChange;

    //本次记录冻结金额变更数目
    private BigDecimal blockChange;

    //当前账户剩余非冻结金额
    private BigDecimal currentToken;

    //当前账户剩余冻结金额
    private BigDecimal blockToken;

    //交易内容或原因
    private String content;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

    @TableLogic
    private Integer deleted;

}
