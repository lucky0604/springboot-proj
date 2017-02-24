package com.lucky.core.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lucky on 1/11/17.
 */
@Controller
@RequestMapping("/userInfo")
public class UserController {

    /**
     * 用户查询
     * @return
     */
    @RequestMapping("/userList")
    public String userInfo() {
        return "userInfo";
    }

    @RequestMapping("/userAdd")
    @RequiresPermissions("userInfo:add")    // 权限管理
    public String userAdd() {
        return "userAdd";
    }
}