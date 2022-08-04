package com.ldy.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
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
@ApiModel(value = "TaskCategory对象", description = "")
public class TaskCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "任务分类ID")
    private Long id;

    @ApiModelProperty(value = "任务分类名称")
    private String name;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建人")
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "修改人")
    private Long updateUser;

    @ApiModelProperty(value = "逻辑删除")
    private Integer deleted;


}
