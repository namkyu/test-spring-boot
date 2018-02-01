package com.kyu.boot.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Project : test-spring-boot
 * @Date : 2018-02-01
 * @Author : nklee
 * @Description :
 */
@Data
public class ResultMain {

    @ApiModelProperty(notes = "파라미터1", required = true)
    private String param1;
    @ApiModelProperty(notes = "파라미터2", required = true)
    private int param2;
    @ApiModelProperty(notes = "파라미터3", required = true)
    private int param3;
}
