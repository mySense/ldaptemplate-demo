package com.zzy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

/**
 * Created by DELL on 2019/3/16.
 */
@Configuration
@EnableConfigurationProperties(LdapProperties.class)
public class LdapConfig {


    @Autowired
    private LdapProperties ldapProperties;


    @Bean(name = {"LdapContextSource"})
    public LdapContextSource contextSource(){
        LdapContextSource ldapContextSource = new LdapContextSource();
        ldapContextSource.setBase(ldapProperties.getBase());
        ldapContextSource.setUrl(ldapProperties.getUrl());
        ldapContextSource.setUserDn(ldapProperties.getUser());
        ldapContextSource.setPassword(ldapProperties.getPassword());
        return ldapContextSource;
    }

    public LdapTemplate ldapTemplate(@Qualifier("LdapContextSource")ContextSource contextSource){
        LdapTemplate ldapTemplate = new LdapTemplate(contextSource);
        ldapTemplate.setDefaultTimeLimit(ldapProperties.getTimeLimit());
        ldapTemplate.setDefaultCountLimit(ldapProperties.getCountLimit());
        ldapTemplate.setIgnorePartialResultException(true);
        return ldapTemplate;
    }

}
