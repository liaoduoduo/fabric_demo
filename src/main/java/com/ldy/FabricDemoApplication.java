package com.ldy;

import com.ldy.common.BaseContext;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ServletComponentScan
//开启事务支持
@EnableTransactionManagement
//开启异步功能
@EnableAsync
@MapperScan("com.ldy.mapper")
public class FabricDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(FabricDemoApplication.class, args);
    }
}
