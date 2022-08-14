package com.ldy;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.ldy.entity.DecideInfo;
import com.ldy.mapper.DecideInfoMapper;
import com.ldy.service.IDecideInfoService;
import com.ldy.util.CamelCaseUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class MapperTest {
    @Autowired
    IDecideInfoService decideInfoService;
    @Autowired
    DecideInfoMapper decideInfoMapper;

    @Test
    void getTaskDetailFiledByTaskId() {
        List<String> taskDetailFiledByTaskId = decideInfoService.getTaskDetailFiledByTaskId(1556275928202477569L);
        for (String decideInfo : taskDetailFiledByTaskId) {
            log.info(decideInfo);
        }
    }

    @Test
    void toCamelCaseDecideInfo() {
        List<DecideInfo> decideInfos = decideInfoMapper.selectList(null);
        for (DecideInfo decideInfo : decideInfos) {
            decideInfo.setName(CamelCaseUtils.underlineToHump(decideInfo.getName()));
            LambdaUpdateWrapper<DecideInfo> decideInfoLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
            decideInfoLambdaUpdateWrapper
                    .set(DecideInfo::getName, CamelCaseUtils.underlineToHump(decideInfo.getName()))
                    .eq(DecideInfo::getId, decideInfo.getId());
            decideInfoService.update(decideInfoLambdaUpdateWrapper);
        }
    }
}
