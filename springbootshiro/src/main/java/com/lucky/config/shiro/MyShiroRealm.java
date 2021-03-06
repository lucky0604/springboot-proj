package com.lucky.config.shiro;

import com.lucky.core.bean.SysPermission;
import com.lucky.core.bean.SysRole;
import com.lucky.core.bean.User;
import com.lucky.core.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

/**
 * Created by lucky on 17-1-11.
 *
 * 身份校验核心类
 * 继承AuthorizingRealm主要需要实现两个方法：
 * doGetAuthenticationInfo();
 * doGetAuthorizationInfo();
 *
 * 其中doGetAuthenticationInfo主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。
 *
 * 至于doGetAuthorizationInfo()是权限控制，当访问到页面的时候，
 * 使用了相应的注解或者shiro标签才会执行此方法否则不会执行，所以如果只是简单的身份认证没有权限的控制的话，那么这个方法可以不进行实现，直接返回null即可。
 */
public class MyShiroRealm extends AuthorizingRealm {
    @Resource
    private UserService userService;

    /**
     * 认证信息(身份验证)
     *
     * Authentication是用来验证用户身份
     * @param token
     * @return
     * @throws javax.naming.AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("MyShiroRealm.doGetAuthenticationInfo");

        // 获取用户输入的帐号
        String username = (String) token.getPrincipal();
        System.out.println(token.getCredentials());

        //通过username从数据库中查找User对象
        // 实际项目中，可根据实际情况做缓存，如果不做，Shiro也有时间间隔，2分钟内不会重复执行该方法
        User user = userService.findByUsername(username);
        System.out.println("--------->> user = " + user);
        if (user == null) {
            return null;
        }

        /**
         * 获取权限信息，可自行根据User, Role, Permission实现
         * 获取之后可以在前端for循环显示所有链接
         */
        // user.setPermissions(userService.findPermissions(user));

        /**
         * 帐号判断
         */
        // 加密方式，交给AuthenticatingRealm使用CredentialsMatcher进行匹配，若不妥可自定义
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user,  // 用户名
                user.getPassword(),   // 密码
                ByteSource.Util.bytes(user.getPassword()),    // salt=username+salt
                getName()    // realm name
        );

        // 明文方式，若用户存在，将此用户存放到登陆认证info中，无需自己做密码对比
        /*
        SimpleAuthenticationInfo authenticationInfo1 = new SimpleAuthenticationInfo(
                user,
                user.getPassword(),
                getName()
        );
        */

        return authenticationInfo;
    }

    /**
     * 此方法调用hasRole.hasPermission时才会进行回调
     *
     * 权限信息（授权）
     * 1,若用户正常退出，缓存清空
     * 2,若用户非正常退出，缓存清空
     * 3,若我们修改用户权限，而用户不退出系统，修改的权限无法立即生效（需手动实现，放入service调用）
     *
     * 权限修改后调用realm中的方法，realm已由spring管理，所以从spring中获取realm实例，调用clearCached方法
     * :Authorization是授权访问控制，对于用户进行的操作授权，证明该用户是否允许当前操作，如访问某个链接，某个资源等
     * @param principals
     * @return
     */
    @Override
    public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
         /*
        * 当没有使用缓存的时候，不断刷新页面的话，这个代码会不断执行，
        * 没有必要每次都重新设置权限信息，所以我们需要放到缓存中进行管理；
        * 当放到缓存中时，这样的话，doGetAuthorizationInfo就只会执行一次了，
        * 缓存过期之后会再次执行。
        */
         System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo");

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principals.getPrimaryPrincipal();

        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
//     UserInfo userInfo = userInfoService.findByUsername(username)


        //权限单个添加;
        // 或者按下面这样添加
        //添加一个角色,不是配置意义上的添加,而是证明该用户拥有admin角色
//     authorizationInfo.addRole("admin");
        //添加权限
//     authorizationInfo.addStringPermission("userInfo:query");


        ///在认证成功之后返回.
        //设置角色信息.
        //支持 Set集合,
        //用户的角色对应的所有权限，如果只使用角色定义访问权限，下面的四行可以不要
//        List<Role> roleList=user.getRoleList();
//        for (Role role : roleList) {
//            info.addStringPermissions(role.getPermissionsName());
//        }
        for (SysRole role: user.getRoleList()) {
            authorizationInfo.addRole(role.getRole());
            for (SysPermission p: role.getPermissions()) {
                authorizationInfo.addStringPermission(p.getPermission());
            }
        }


        //设置权限信息.
//     authorizationInfo.setStringPermissions(getStringPermissions(userInfo.getRoleList()));
        return authorizationInfo;
    }

    /**
     * 将权限对象中的权限code取出.
     * @param permissions
     * @return
     */
//  public Set<String> getStringPermissions(Set<SysPermission> permissions){
//     Set<String> stringPermissions = new HashSet<String>();
//     if(permissions != null){
//         for(SysPermission p : permissions) {
//            stringPermissions.add(p.getPermission());
//          }
//     }
//       return stringPermissions;
//  }

}
