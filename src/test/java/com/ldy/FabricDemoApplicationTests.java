package com.ldy;

import com.ldy.mapper.UserTaskMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

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

}
