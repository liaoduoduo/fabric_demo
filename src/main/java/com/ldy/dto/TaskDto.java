package com.ldy.dto;

import com.ldy.entity.Task;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TaskDto extends Task {
    // 用于增加悬赏
    private BigDecimal newToken;
}
