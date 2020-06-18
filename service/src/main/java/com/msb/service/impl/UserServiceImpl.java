package com.msb.service.impl;

import com.msb.dao.UserMapper;
import com.msb.domain.SysUser;
import com.msb.domain.User;
import com.msb.exception.user.UserNotExistsException;
import com.msb.exception.user.UserPasswordNotMatchException;
import com.msb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public void save(User user) {
        userMapper.save(user);
    }

    @Override
    public SysUser getUserByName(String loginName) {
        return userMapper.getUserByName(loginName);
    }

    @Override
    public SysUser login(String loginName, String password) {
        SysUser user = this.getUserByName(loginName);
        if(user == null){
            throw new UserNotExistsException();
        }
        if(!user.getPassword().equals(password)){
            throw new UserPasswordNotMatchException();
        }
        return user;
    }
}
