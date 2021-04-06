package com.zdk.dao;

import com.zdk.pojo.User;

import java.util.List;

/**
 * @author zdk
 * @date 2021/3/28 15:43
 */
public interface UserMapper {
    int addUser(User user);
    User getUserById(int id);
}
