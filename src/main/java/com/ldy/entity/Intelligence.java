package com.ldy.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author ldy
 * @Date 2022/6/25 16:49
 * @ClassName Intelligence
 * @Description 情报实体类
 * @Version v1.0
 */
@Data
public class Intelligence implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    //上传用户id
    private Long userId;

    private Long categoryId;

    private String name;

    private String fileHash;

    //图片
    private String image;

    //情报描述
    private String description;

    //0 停售 1 起售
    private Integer status;

    //情报token价值
    private BigDecimal price;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

}
