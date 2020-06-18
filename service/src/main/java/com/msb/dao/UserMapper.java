package com.msb.dao;

import com.msb.domain.SysUser;
import com.msb.domain.User;

public interface UserMapper {
    public void save(User user);

    public SysUser getUserByName(String username);
}
