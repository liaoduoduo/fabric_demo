package com.ldy;

import com.ldy.common.BaseContext;
import com.ldy.entity.DecideInfo;
import com.ldy.entity.DecideInfoCategory;
import com.ldy.entity.TaskCategory;
import com.ldy.service.IDecideInfoCategoryService;
import com.ldy.service.IDecideInfoService;
import com.ldy.service.ITaskCategoryService;
import com.ldy.utils.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataWriteTest {

    @Autowired
    ITaskCategoryService taskCategoryService;

    @Autowired
    IDecideInfoCategoryService decideInfoCategoryService;

    @Autowired
    IDecideInfoService decideInfoService;
    private final String excelPath = "/usr/local/dev/java/fabric-demo/src/main/resources/excel/";

    @Test
    public void addTaskCategory() throws Exception {
        BaseContext.setCurrentId(1L);
        List<TaskCategory> taskCategories = IOUtils.readTaskCategoryFromExcel(excelPath + "task_category.xlsx");
        boolean b = taskCategoryService.saveBatch(taskCategories);
        System.out.println(b);
    }

    @Test
    public void addDecideInfoCategory() throws Exception {
        BaseContext.setCurrentId(1L);
        List<DecideInfoCategory> decideInfoCategories = IOUtils.readDecideInfoCategoryFromExcel(excelPath + "decide_info_category.xlsx");
        boolean b = decideInfoCategoryService.saveBatch(decideInfoCategories);
        System.out.println(b);
    }

    @Test
    public void addDecideInfo() throws Exception {
        BaseContext.setCurrentId(1L);
        List<DecideInfo> decideInfos = IOUtils.readDecideInfoFromExcel(excelPath + "decide_info.xlsx");
        boolean b = decideInfoService.saveBatch(decideInfos);
        System.out.println(b);
    }
}
