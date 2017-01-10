package com.lucky.controller;

import com.lucky.dao.UserDao;
import com.lucky.dto.ErrorReporter;
import com.lucky.model.User;
import com.lucky.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by lucky on 1/9/17.
 */
@RestController
public class AuthController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ErrorReporter doLogin(String username, String password) {
        return loginService.login(username, password);
    }

    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public ErrorReporter doReg(String username, String password, String email, String nickname) {
        return loginService.reg(username, password, email, nickname);
    }

    @RequestMapping(value = "/users")
    public List<User> findAll() {
        return userDao.findAll();
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable("id") int id) {
        try {
            userDao.delete(id);
            return String.format("Delete successful id [%s]", id);
        } catch (Exception e) {
            return String.format("problem");
        }
    }
}