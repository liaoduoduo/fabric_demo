package com.ldy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String loginPage(){

        return "redirect:/backend/page/login/login.html";
    }
}
