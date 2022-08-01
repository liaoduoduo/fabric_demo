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
@ApiModel(value = "Cotasking对象", description = "协同任务")
public class Cotasking implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("协同任务主键")
    private Long id;

    @ApiModelProperty(value = "协同任务简要描述")
    private String name;

    @ApiModelProperty(value = "协同任务描述")
    private String description;

    @ApiModelProperty(value = "协同任务的状态,1表示激活，0表示未激活")
    private Integer status;

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

    @ApiModelProperty(value = "逻辑删除")
    private Integer deleted;


}
