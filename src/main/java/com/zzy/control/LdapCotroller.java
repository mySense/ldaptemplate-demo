package com.zzy.control;

import com.zzy.dal.User;
import com.zzy.service.impl.LdapServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by DELL on 2019/3/16.
 */
@Controller
@RequestMapping("/ldap")
public class LdapCotroller {

    @Autowired
    private LdapServiceImpl ldapService;


    @ResponseBody
    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public List<User> search(){

        List<User> users = ldapService.queryUser();
        return  users;
    }


    @ResponseBody
    @RequestMapping(value = "/auth",method = RequestMethod.POST)
    public Boolean auth(String name,String password){

        Boolean result = ldapService.authUser(name, password);
        return  result;
    }

}
