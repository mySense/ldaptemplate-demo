package com.zzy.dal;

/**
 * Created by DELL on 2019/3/16.
 */
public class User {

    private String cn;
    private String mail;
    private String sn;

    public User() {
    }

    public User(String cn, String mail, String sn) {
        this.cn = cn;
        this.mail = mail;
        this.sn = sn;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }
}
