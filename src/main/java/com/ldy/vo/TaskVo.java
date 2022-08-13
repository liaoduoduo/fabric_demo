package com.ldy.vo;

import com.ldy.entity.Task;
import lombok.Data;

@Data
public class TaskVo extends Task {
    // 此任务的接单人数
    private Integer jiedanCount;
    // 任务发布者名称
    private String createUserName;
    private String cotaskName;
    private String taskCategoryName;
    private String decideInfoCategoryName;
}
