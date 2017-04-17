package com.kyu.boot;

import com.kyu.boot.entity.Account;
import com.kyu.boot.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by nklee on 2017-04-10.
 */
@Slf4j
@Controller
public class HelloController {

    @Autowired
    private AccountRepository accountRepository;

    @ResponseBody
    @RequestMapping("/")
    public String hello() {
        return "My first Spring boot!!";
    }

    @Value("${welcome.message}")
    private String message = "Hello World";


    /**
     * @return
     */
    @ResponseBody
    @RequestMapping("/convertJodaTimefromDateType")
    public List<Account> convertJodaTimeFromDateType() {
        List<Account> accountList = accountRepository.findAll();
        return accountList;
    }

    @RequestMapping("/thymleafTest")
    public String thymleafTest(Map<String, String> model) {
        model.put("message", message);
        log.info("message11 : {}", message);
        return "helloThymleaf";
    }
}
