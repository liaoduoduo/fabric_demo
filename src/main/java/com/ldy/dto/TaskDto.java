package com.ldy.dto;

import com.ldy.entity.Task;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TaskDto extends Task {
    // 用于增加悬赏
    private BigDecimal newToken;

    // 用于悬赏任务的上下架 1表示上架 0 表示下架
    private Long[] ids;
    private Integer newStatus;
}
