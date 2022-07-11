package com.ldy.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author ldy
 * @Date 2022/6/25 15:19
 * @ClassName User
 * @Description 用户信息
 * @Version v1.0
 */
@Data
public class User implements Serializable {
    //MySQL中的用户实体类：
    //区块链中的实体类：id，用户名，姓名，密码，电话，性别，身份证号，状态，不需要更新时间字段
    private static final long serialVersionUID = 1L;

    private Long id;

    //用户名
    private String username;

    //姓名
    private String name;

    //密码
    private String password;

    //电话
    private String phone;

    //性别
    private String sex;

    //身份证号
    private String idNumber;

    //状态
    private Integer status;

    //用户贡献度
    //private Long contribution;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;
}
