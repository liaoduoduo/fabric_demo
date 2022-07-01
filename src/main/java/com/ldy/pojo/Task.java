package com.ldy.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author ldy
 * @Date 2022/7/1 16:56
 * @ClassName Task
 * @Description 研判任务实体类
 * @Version v1.0
 */
@Data
public class Task implements Serializable {
    private Long id;

    //研判任务名
    private String name;

    //研判任务阶段
    private String stage;

    //研判任务悬赏
    private Integer price;

    //研判任务描述
    private String description;

    //研判任务结果，默认未结束
    private String result;

    //研判任务打分，默认0分
    private Integer score;

    //研判任务状态，0停止，1启动
    private Integer status;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;


}
