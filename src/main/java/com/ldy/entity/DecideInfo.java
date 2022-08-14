package com.ldy.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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
@ApiModel(value = "DecideInfo对象", description = "")
@ToString
public class DecideInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty(value = "存储对应判定信息分类的字段，用于查询，比如select a,b,c from A")
    private String name;

    @ApiModelProperty(value = "对应字段的中文")
    private String nameZh;

    @ApiModelProperty(value = "研判任务信息分类id")
    private Long decideInfoCategoryId;

    @ApiModelProperty(value = "对应信息字段的权重")
    private Double weight;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    @ApiModelProperty(value = "修改人")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "逻辑删除")
    private Integer deleted;


}
