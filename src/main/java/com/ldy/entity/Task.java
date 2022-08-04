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
 * @since 2022-07-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "Task对象", description = "")
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("普通研判任务主键")
    private Long id;

    @ApiModelProperty(value = "研判任务简要需求")
    private String name;

    @ApiModelProperty(value = "所属协同任务id")
    private Long cotaskingId;

    @ApiModelProperty(value = "任务分类id")
    private Long taskCategoryId;

    @ApiModelProperty(value = "待研判任务分类id，与任务分类id作为联合主键，用于确定相应需填写的内容")
    private Long decideInfoCategoryId;

    @ApiModelProperty(value = "任务开始时间")
    private LocalDateTime beginTime;

    @ApiModelProperty(value = "任务结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "任务金额")
    private BigDecimal token;

    @ApiModelProperty(value = "任务是否公开，作为悬赏")
    private Integer open;

    @ApiModelProperty(value = "是否激活")
    private Integer status;

    @ApiModelProperty(value = "这里可以用来设置任务可以被那些人领取，为了测试这里仅以单位作为接单策略（仅适用私有任务）")
    private String policy;

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

    @ApiModelProperty(value = "是否完成")
    private Integer finished;

    @ApiModelProperty(value = "任务评价")
    private String evaluation;

    @ApiModelProperty(value = "逻辑删除")
    private Integer deleted;


}
