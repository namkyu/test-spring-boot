package com.kyu.boot.service;

import com.kyu.boot.entity.NamkyuUser;
import com.kyu.boot.repository.HelloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class HelloService {

    @Autowired
    private HelloRepository helloRepository;

    public void getUserByOccurNPE() {
        NamkyuUser namkyuUser = new NamkyuUser();
        namkyuUser.setUserId("namkyu5");
        helloRepository.save(namkyuUser);

        // occurs NPE
        String test = null;
        test.isEmpty();
    }

    public void getUser() {
        NamkyuUser namkyuUser = new NamkyuUser();
        namkyuUser.setUserId("namkyu5");
        helloRepository.save(namkyuUser);
    }


    public List<NamkyuUser> getUsers() {
        return helloRepository.findAll();
    }
}
