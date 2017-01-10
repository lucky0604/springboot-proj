package com.lucky.service;

import com.lucky.dao.UserDao;
import com.lucky.dto.ErrorReporter;
import com.lucky.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by lucky on 1/9/17.
 */
@Service
public class LoginService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private HttpSession httpSession;

    public ErrorReporter login(String username, String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Iterable<User> users = userDao.findByUsername(username);
        Iterator iterator = users.iterator();
        if (iterator.hasNext()) {
            User user = (User) iterator.next();
            if (passwordEncoder.matches(password, user.getPassword())) {
                httpSession.setAttribute("user", user);
                return new ErrorReporter(0, "success");
            } else {
                return new ErrorReporter(1, "password error");
            }
        } else {
            return new ErrorReporter(2, "no account");
        }
    }

    public ErrorReporter reg(String username, String password, String email, String nickname) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        password = passwordEncoder.encode(password);
        Collection<User> users = (Collection<User>) userDao.findByUsername(username);
        if (users.isEmpty()) {
            try {
                User user = new User(username, password, email, nickname);
                userDao.save(user);
                return new ErrorReporter(0, "success");
            } catch (Exception e) {
                e.printStackTrace();
                return new ErrorReporter(-1, "unknown error");
            }
        } else {
            return new ErrorReporter(3, "duplication error");
        }
    }

    public int isLogin() {
        if (httpSession.getAttribute("user") != null) {
            return 1;
        } else {
            return 0;
        }
    }
}
