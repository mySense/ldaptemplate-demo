package com.zzy.service.impl;

import com.zzy.dal.User;
import com.zzy.service.LdapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.stereotype.Service;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import java.util.List;

/**
 * Created by DELL on 2019/3/16.
 */
@Service("ldapService")
public class LdapServiceImpl implements LdapService {

    @Autowired
    private LdapTemplate ldapTemplate;

    /**
     * 用户查询
     * @return 返回用户集合信息
     */
    @Override
    public List<User> queryUser() {
        AndFilter andFilter = new AndFilter();
        andFilter.and(new EqualsFilter("objectClass","user"));
        LdapQuery query = LdapQueryBuilder.query().where("objectClass").is("user");
        List<User> search = ldapTemplate.search(query, new AttributesMapper<User>() {
            @Override
            public User mapFromAttributes(Attributes attributes) throws NamingException {

                String cn = (String) attributes.get("cn").get(0);
                String mail = (String) attributes.get("mail").get(0);
                String sn = (String) attributes.get("sn").get(0);

                return new User(cn, mail, sn);
            }
        });
        return search;
    }

    /**
     * 用户认证
     * @param password
     * @return  返回Boolean
     */
    @Override
    public Boolean authUser(String name,String password) {
        LdapQuery query = LdapQueryBuilder.query().where("objectClass").is("user").and("cn").is(name);

        try {
            ldapTemplate.authenticate(query,password);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
