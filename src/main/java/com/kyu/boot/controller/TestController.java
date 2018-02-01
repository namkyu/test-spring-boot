package com.kyu.boot.controller;

import com.kyu.boot.model.MultipartBean;
import com.kyu.boot.model.Product;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by nklee on 2017-04-10.
 */
@Slf4j
@RestController
public class TestController {

    @Value("${welcome.message}")
    private String message = "Hello World";

    @GetMapping("/thymleaf")
    public String thymleafHome(Map<String, Object> model) {
        Product product = new Product("자전거", 1000, new DateTime().toDate());
        model.put("product", product);
        model.put("productList", getProductList());
        model.put("html", "test <b>hi</b>");
        model.put("name", "namkyu");
        return "thymleaf";
    }

    @ResponseBody
    @PostMapping(value = "/multipart/test")
    public String upload(@ModelAttribute MultipartBean multipartBean) {
        MultipartFile file = multipartBean.getFile();
        String sourceFileName = file.getOriginalFilename();
        return sourceFileName;
    }

    private List<Product> getProductList() {
        List<Product> list = new ArrayList<>();
        list.add(new Product("자전거1", 1000, new DateTime().toDate()));
        list.add(new Product("자전거2", 2000, new DateTime().toDate()));
        list.add(new Product("자전거3", 3000, new DateTime().toDate()));
        return list;
    }
}


