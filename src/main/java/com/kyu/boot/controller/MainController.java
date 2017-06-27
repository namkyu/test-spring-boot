package com.kyu.boot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
public class MainController {

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Model model) {
        return "main";
    }
}


