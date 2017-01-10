package com.lucky.config.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by lucky on 1/10/17.
 *
 * Shiro配置
 *
 * Shiro核心通过Filter来实现，类似于SpringMVC通过DispatchServlet来主控制一样
 * 因为是通过Filter，即一般是通过URL规则来进行过滤和权限校验，所以需要定义一系列关于URL的规则和访问权限
 */
@Configuration
public class ShiroConfiguration {
    /**
     * ShiroFilterFactoryBean处理拦截资源文件问题
     * 注意：单独一个ShiroFilterFactoryBean配置会报错，因为在初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     *
     * FilterChain定义说明
     * 1,一个URL可以配置多个Filte，使用逗号分隔
     * 2，当设置多个过滤器时，全部验证通过，才视为通过
     * 3，部分过滤器可指定参数，如perms, roles
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        System.out.println("ShiroConfiguration.shiroFilter()");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必需设置SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

        // 配置退出过滤器，其中的具体退出代码Shiro已实现
        filterChainDefinitionMap.put("/logout", "logout");

        // 过滤链定义，从上向下顺序执行，一般将/**放在最下面
        // authc: 所有url都必需认证通过才可以访问，anon: 所有url都可以匿名访问
        filterChainDefinitionMap.put("/**", "authc");

        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login");

        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");

        // 未授权界面
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        return securityManager;
    }
}
