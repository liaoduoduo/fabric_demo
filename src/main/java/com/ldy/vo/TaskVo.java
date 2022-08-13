package com.ldy.vo;

import com.ldy.entity.Task;
import lombok.Data;

@Data
public class TaskVo extends Task {
    // 此任务的接单人数
    private Integer jiedanCount;
    private String cotaskName;
    private String taskCategoryName;
    private String decideInfoCategoryName;
}
