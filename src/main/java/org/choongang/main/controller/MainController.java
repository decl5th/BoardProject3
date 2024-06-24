package org.choongang.main.controller;

import org.choongang.global.config.annotations.Controller;
import org.choongang.global.config.annotations.GetMapping;
import org.choongang.global.config.annotations.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping
    public String index() {

        return "main/index";
    }
}
