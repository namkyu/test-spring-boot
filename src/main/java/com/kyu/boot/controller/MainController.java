package com.kyu.boot.controller;

import com.kyu.boot.model.ResultMain;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Api(value = "main", description = "main controller")
public class MainController {

    @ApiOperation(value = "메인 페이지")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "area", value = "지역", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "param1", value = "파라미터1", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "param2", value = "파라미터2", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "param3", value = "파라미터3", required = false, dataType = "int", paramType = "query")
    })
    @GetMapping(value = "/main/{area}")
    public ResultMain main(@PathVariable String area
            , @RequestParam String param1
            , @RequestParam int param2
            , @RequestParam(required = false, defaultValue = "0") int param3
            , @RequestHeader("remoteAddr") String remoteAddr) {

        ResultMain resultMain = new ResultMain();
        resultMain.setParam1(param1);
        resultMain.setParam2(param2);
        resultMain.setParam3(param3);
        return resultMain;
    }
}


