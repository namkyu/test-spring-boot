package com.kyu.boot.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.File;
import java.io.FileInputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @Project : test-spring-boot
 * @Date : 2017-12-06
 * @Author : nklee
 * @Description :
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void multipart테스트() throws Exception {
        FileInputStream fi1 = new FileInputStream(new File("E:\\test\\image\\a.jpg"));
        MockMultipartFile mockFile = new MockMultipartFile("file", "a.jpg", "multipart/form-data", fi1);
        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/multipart/test")
                .file(mockFile)
                .param("name", "abc").param("email", "abc@gmail.com").param("phone", "1234567890"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void httpclient_3x버전테스트() {
        Http http = new Http("http://127.0.0.1:20010/multipart/test");
        String result = http.addParam("name", "abc")
                .addParam("email", "abc@gmail.com")
                .addParam("phone", "11111111")
                .addParam("file", new File("E:\\test\\image\\a.jpg"))
                .submit();

        assertThat("a.jpg", is(result.trim()));
    }
}