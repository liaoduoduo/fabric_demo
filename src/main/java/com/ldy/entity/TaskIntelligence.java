package com.ldy.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author ldy
 * @Date 2022/7/1 17:08
 * @ClassName TaskIntelligence
 * @Description 研判任务包含的情报实体类，一个研判任务包含多个情报
 * @Version v1.0
 */
@Data
public class TaskIntelligence implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    //研判任务id
    private Long taskId;

    //研判任务包含的情报id list
    private String value;



    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


    @TableField(fill = FieldFill.INSERT)
    private Long createUser;


    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;
}
