package com.kyu.boot.model;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * @Project : test_project
 * @Date : 2017-04-25
 * @Author : nklee
 * @Description :
 */
@Data
public class MultipartBean {
    private MultipartFile file;
    private String name;
    private String email;
    private String phone;

}
