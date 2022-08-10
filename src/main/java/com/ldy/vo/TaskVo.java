package com.ldy.vo;

import com.ldy.entity.Task;
import lombok.Data;

@Data
public class TaskVo extends Task {
    private String taskCategoryName;
    private String decideInfoCategoryName;
}
