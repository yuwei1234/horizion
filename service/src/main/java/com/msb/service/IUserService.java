package com.msb.service;

import com.msb.domain.SysUser;
import com.msb.domain.User;

public interface IUserService {

    public void save(User user);

    public SysUser getUserByName(String loginName);

    public SysUser login(String loginName, String password);
}
