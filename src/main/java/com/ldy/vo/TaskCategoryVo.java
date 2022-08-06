package com.ldy.vo;

import com.ldy.entity.DecideInfoCategory;
import com.ldy.entity.TaskCategory;
import lombok.Data;

import java.util.List;

@Data
public class TaskCategoryVo {

    private Long id;
    private String name;
    private List<DecideInfoCategory> children;


}
