package com.kyu.boot;

import com.kyu.boot.entity.Account;
import com.kyu.boot.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by nklee on 2017-04-10.
 */
@Controller
public class HelloController {

    @Autowired
    private AccountRepository accountRepository;

    @ResponseBody
    @RequestMapping("/")
    public String hello() {
        return "My first Spring boot!!";
    }


    /**
     * @return
     */
    @ResponseBody
    @RequestMapping("/convertJodaTimefromDateType")
    public List<Account> convertJodaTimeFromDateType() {
        List<Account> accountList = accountRepository.findAll();
        return accountList;
    }
}
