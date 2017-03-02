package com.lucky.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by lucky on 3/1/17.
 */
@Controller
@RequestMapping("/main")
@Api(value = "/main", description = "主要入口控制器")
public class MainController {
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ApiOperation(value = "打开用户登录界面", notes = "返回用户登录界面")
    public String userLogin() {
        return "userLogin";
    }
}
