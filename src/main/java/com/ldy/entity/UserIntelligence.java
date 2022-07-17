package com.ldy.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author ldy
 * @Date 2022/7/8 22:21
 * @ClassName UserIntelligence
 * @Description 用户购买的情报
 * @Version v1.0
 */
@Data
public class UserIntelligence {
    private Long id;
    private Long userId;

    //intelligence表主键
    private Long intelligenceId;
    //intelligence表主键对应fileHash字段
    private String fileHash;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;
}
