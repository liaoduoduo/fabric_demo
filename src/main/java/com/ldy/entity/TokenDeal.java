package com.ldy.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author sunqing
 * @since 2022-08-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="TokenDeal对象", description="")
public class TokenDeal implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "支付方")
    private Long fromUser;

    @ApiModelProperty(value = "收款方")
    private Long toUser;

    @ApiModelProperty(value = "当收到的是任务时")
    private Long taskId;

    @ApiModelProperty(value = "当购买的是情报时")
    private Long intelligenceId;

    @ApiModelProperty(value = "交易金额")
    private BigDecimal value;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建人")
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    @ApiModelProperty(value = "修改人")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

    @ApiModelProperty(value = "逻辑删除")
    private Integer deleted;


}
