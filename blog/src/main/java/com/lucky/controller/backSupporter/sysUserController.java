package com.lucky.controller.backSupporter;

import com.google.gson.Gson;
import com.lucky.domain.ResponseObj;
import com.lucky.domain.User;
import com.lucky.service.userService.UserService;
import com.lucky.utils.EncryptUtils;
import com.lucky.utils.GsonUtils;
import com.lucky.utils.PublicUtil;
import com.lucky.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * Created by lucky on 3/1/17.
 */
@Controller
@RequestMapping(value = "/user")
@Api(value = "/user", description = "用户相关")
public class sysUserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", produces = {APPLICATION_JSON_UTF8_VALUE}, method = RequestMethod.POST)
    @ApiOperation(value = "用户登录", notes = "用户登录的接口，输入用户名和密码登录", response = User.class)
    @ResponseBody
    public Object userLogin(HttpServletRequest request, HttpServletResponse response, @ApiParam(value = "用户名不能为空，否则不允许登录"
    , required = true) @RequestParam("userLogin") String userLogin, @ApiParam(value = "用户密码不能为空且必需为16位小写md5，否则不允许登录", required = true)
                            , @RequestParam("userPass") String userPass) {
        ResponseObj<User> responseObj = new ResponseObj<>();
        User user;
        if (PublicUtil.isJsonRequest(request)) {    // 确认是否是post的json提交
            try {
                user = new GsonUtils().jsonRequest2Bean(request.getInputStream(), User.class);    // 转换为指定类型的对象
                userLogin = user.getUserLogin();
                userPass = user.getUserPass();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (StringUtils.isEmpty(userLogin) || StringUtils.isEmpty(userPass)) {
            responseObj.setCode(ResponseObj.FAILED);
            responseObj.setMsg("用户密码不能为空！");
            return new GsonUtils().toJson(responseObj);
        }

        user = userService.findOneById(userLogin);

        if (null == user) {
            responseObj.setCode(ResponseObj.EMPTY);
            responseObj.setMsg("用户不存在！");
            return new GsonUtils().toJson(responseObj);
        }

        userPass = userPass.toLowerCase();    // 将大写md5转为小写md5

        if (userPass.length() > 16 && userPass.length() == 32) {    // 32位小写转换为16位小写
            userPass = userPass.substring(8, 24).toLowerCase();
        } else if (userPass.length() > 32) {
            responseObj.setCode(ResponseObj.FAILED);
            responseObj.setMsg("密码不规范！");
            return new GsonUtils().toJson(responseObj);
        }

        String encryptPassword = EncryptUtils.
    }
}
