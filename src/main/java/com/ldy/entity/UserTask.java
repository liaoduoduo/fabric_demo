package com.ldy.entity;

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
@ApiModel(value = "UserTask对象", description = "")
public class UserTask implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty(value = "研判任务id")
    private Long taskId;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "任务详情id")
    private Long taskDetailId;

    @ApiModelProperty(value = "提交时间")
    private LocalDateTime submitTime;

    @ApiModelProperty(value = "是否被接受")
    private Integer accepted;

    @ApiModelProperty(value = "贡献度，每一个任务")
    private String contribution;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建人")
    private Long createUser;

    @ApiModelProperty(value = "修改人")
    private Long updateUser;

    @ApiModelProperty(value = "逻辑删除")
    private Integer deleted;
}
