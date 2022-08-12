package com.ldy;

import com.ldy.mapper.UserTaskMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
class FabricDemoApplicationTests {

    @Autowired
    UserTaskMapper userTaskMapper;

    @Test
    void selectUserTaskCountInCotaskingTest() {
        List<Long> longs = new ArrayList<>();
        longs.add(1555118193016623106L);
        Integer integer = userTaskMapper.selectUserTaskCountInCotasking(longs);
        System.out.println(integer);
    }

    @Test
    void mathTest() {
        BigDecimal currentToken = new BigDecimal(0.5);
        BigDecimal blockToken = new BigDecimal(0.8);
        BigDecimal totalBlock = new BigDecimal(0);
        BigDecimal[] taskTokens = {new BigDecimal(0.5)};
        for (BigDecimal taskToken : taskTokens) {
            totalBlock = totalBlock.add(taskToken);
        }
        log.info("释放值" + totalBlock);
        log.info("增加值" + totalBlock.negate());
        log.info(String.valueOf(totalBlock));

    }

}
