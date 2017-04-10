package com.kyu.boot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by nklee on 2017-04-10.
 */
@Controller
public class HelloController {

    @ResponseBody
    @RequestMapping("/")
    public String hello() {
        return "My first Spring boot!!";
    }
}
