package com.zzy.service;

import com.zzy.dal.User;

import java.util.List;

/**
 * Created by DELL on 2019/3/16.
 */
public interface LdapService {
    List<User> queryUser();
    Boolean authUser(String name,String password);
}
