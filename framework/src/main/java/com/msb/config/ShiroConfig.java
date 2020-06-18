package com.msb.config;

import com.msb.shiro.realm.UserRealm;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

@Configuration
public class ShiroConfig {

    @Value("${shiro.user.loginUrl}")
    private String loginUrl;

    @Value("${shiro.user.unauthorizedUrl}")
    private String unauthorizedUrl;

    @Value("${shiro.user.indexUrl}")
    private String indexUrl;

    @Value("${shiro.cookie.domain}")
    private String domain;

    @Value("$shiro.cookie.path}")
    private String path;

    @Value("${shiro.cookie.httpOnly}")
    private boolean httpOnly;

    @Value("${shiro.cookie.maxAge}")
    private int maxAge;

    @Value("${shiro.session.expireTime}")
    private String expireTime;

    @Value("${shiro.session.dbSyncPeriod}")
    private String dbSyncPeriod;

    @Value("${shiro.session.validationInterval}")
    private String validationInterval;

    @Value("${shiro.session.maxSession}")
    private String maxSession;

    @Value("${shiro.session.kickoutAfter}")
    private String kickoutAfter;

    @Bean
    public UserRealm userRealm(){
        UserRealm userRealm = new UserRealm();
        return userRealm;
    }

//    @Bean
//    public SessionManager sessionManager(){
//        SessionManager
//    }

    @Bean
    public SecurityManager securityManager(UserRealm userRealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(userRealm);
        defaultWebSecurityManager.setRememberMeManager(rememberMeManager());
        //defaultWebSecurityManager.setSessionManager();
        return defaultWebSecurityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 身份认证失败，则跳转到登录页面的配置
        shiroFilterFactoryBean.setLoginUrl(loginUrl);
        // 权限认证失败，则跳转到指定页面
        shiroFilterFactoryBean.setUnauthorizedUrl(unauthorizedUrl);
        // Shiro连接约束配置，即过滤链的定义
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/favicon.ico**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        // 不需要拦截的访问
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/captcha/getCaptcha", "anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * cookie 属性设置
     */
    public SimpleCookie rememberMeCookie() {
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        cookie.setDomain(domain);
        cookie.setPath(path);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(maxAge * 24 * 60 * 60);
        return cookie;
    }

    /**
     * 记住我
     */
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        cookieRememberMeManager.setCipherKey(Base64.decode("fCq+/xW488hMTCD+cmJ3aQ=="));
        return cookieRememberMeManager;
    }
}
