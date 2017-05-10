package com.kyu.boot;

import com.kyu.boot.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by nklee on 2017-04-10.
 */
@Slf4j
@Controller
public class TestController {

    @ResponseBody
    @RequestMapping("/hello")
    public String hello() {
        return "My first Spring boot!!";
    }

    @Value("${welcome.message}")
    private String message = "Hello World";

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Model model) {
        return "main";
    }

    @RequestMapping("/thymleafTest")
    public String thymleafTest(Map<String, String> model) {
        model.put("message", message);
        return "helloThymleaf";
    }

    @RequestMapping("/thymleaf/home")
    public String thymleafHome(Map<String, Object> model) {
        Product product = new Product("자전거", 1000, new DateTime().toDate());
        model.put("product", product);
        model.put("productList", getProductList());
        model.put("html", "test <b>hi</b>");
        model.put("name", "namkyu");
        return "home";
    }

    private List<Product> getProductList() {
        List<Product> list = new ArrayList<>();
        list.add(new Product("자전거1", 1000, new DateTime().toDate()));
        list.add(new Product("자전거2", 2000, new DateTime().toDate()));
        list.add(new Product("자전거3", 3000, new DateTime().toDate()));
        return list;
    }
}


