package com.ldy.dto;

import com.ldy.pojo.Task;
import com.ldy.pojo.TaskIntelligence;
import com.ldy.pojo.TaskUser;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author ldy
 * @Date 2022/7/1 17:13
 * @ClassName TaskDto
 * @Description Task实体类数据传输对象
 * @Version v1.0
 */
@Data
public class TaskDto extends Task implements Serializable {
    private List<TaskUser> users = new ArrayList<>();
    private List<TaskIntelligence> intelligences = new ArrayList<>();
}
