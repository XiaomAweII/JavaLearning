package org.xiaoweii.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 创建控制器
 * @author xiaoweii
 * @create 2024-12-07 17:36
 */
@Controller
@RequestMapping("/hello")
public class HelloController {
    @RequestMapping("/world")
    @ResponseBody
    public String hello() {
        System.out.println("=======hello world=======");
        return "hello";
    }
}
