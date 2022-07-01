package com.ldy.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author ldy
 * @Date 2022/7/1 17:11
 * @ClassName TaskUser
 * @Description 领取研判任务的用户实体类，一个研判任务有多个用户领取
 * @Version v1.0
 */
@Data
public class TaskUser implements Serializable {
    private Long id;

    //研判任务id
    private Long taskId;

    //领取研判任务的用户id list
    private String value;
}
