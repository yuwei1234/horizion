package com.msb.shiro.realm;

import com.msb.domain.SysUser;
import com.msb.exception.user.UserNotExistsException;
import com.msb.exception.user.UserPasswordNotMatchException;
import com.msb.service.DeptService;
import com.msb.service.IUserService;
import com.msb.service.RoleService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    private static final Logger log = LoggerFactory.getLogger(UserRealm.class);

    @Autowired
    private IUserService iUserService;

    @Autowired
    private DeptService deptService;

    @Autowired
    private RoleService roleService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        String loginName = token.getUsername();
        String password = new String(token.getPassword());
        SysUser user = null;
        try {
            user = iUserService.login(loginName, password);
        }catch (UserNotExistsException e){
            throw new UnknownAccountException(e.getMessage(), e);
        }catch (UserPasswordNotMatchException e){
            throw new IncorrectCredentialsException(e.getMessage(), e);
        }catch (Exception e){
            log.info("对用户[" + loginName + "]进行登录验证..验证未通过{}", e.getMessage());
            throw new AuthenticationException(e.getMessage(), e);
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
        return info;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {


        return null;
    }


}
