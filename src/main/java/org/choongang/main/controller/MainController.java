package org.choongang.main.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.choongang.global.config.annotations.Controller;
import org.choongang.global.config.annotations.GetMapping;
import org.choongang.global.config.annotations.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping
    public String index(HttpServletRequest request) {
        request.setAttribute("addCss", new String[] {"main/style"});
        return "main/index";
    }
}
