package com.lucky.intercepter;

import com.lucky.domain.User;
import com.lucky.service.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lucky on 3/1/17.
 */
public class LoginHandlerInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getServletPath();
        String userId;    // 登陆成功后写入session的用户id
        User user;    // 通过用户ID查询到的用户信息

        /**
         * 拦截的网址是需要最少是作者权限才能进行编辑，所以这里需要限制访问
         * 同时可以看到，登陆页面必需所有人都可以访问，但是如果已经登陆成功，session在有效期内，登陆界面就不应再展示给用户
         */
        if (!path.matches(".*/((endSupport)|(commit*)).*")) {
            if (path.contains("/main/login")) {    // 已经登陆且身份信息且没有过期，直接跳转到后端主页
                try {
                    userId = request.getSession().getAttribute("userId").toString();
                    user = userService.findOneById(userId);
                    if (request.getRequestedSessionId().equals(user.getUserSessionId())) {    // 前面用户登陆后会存入请求的sessionId和当前的sessionId对比
                        response.sendRedirect(request.getContextPath() + "/endSupport/index");
                        return false;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return true;
                }
            }
            return true;
        } else {
            try {
                userId = request.getSession().getAttribute("userId").toString();
                user = userService.findOneById(userId);
                if (!request.getRequestedSessionId().equals(user.getUserSessionId())) {    // 前面用户登陆后会存入请求的sessionId和当前的sessionId对比
                    throw new Exception("用户信息错误！");
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect(request.getContextPath() + "/main/login");
                return false;
            }
        }
    }
}
