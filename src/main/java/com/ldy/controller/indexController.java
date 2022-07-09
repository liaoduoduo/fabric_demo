package com.ldy.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author ldy
 * @Date 2022/7/9 14:39
 * @ClassName indexController
 * @Description TODO
 * @Version v1.0
 */

@Slf4j
@Controller
public class indexController {
    @RequestMapping("/")
    public String index(){
        log.info("首页");
        return "redirect:/backend/page/login/login.html";
    }
}
