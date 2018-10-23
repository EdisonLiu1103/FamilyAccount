package com.example.edison.familyacoount.model;

/**
 * 密码实体类
 */


public class Tb_pwd {
    private String password ;           //储存密码

    public Tb_pwd(){
        super();
    }
    public Tb_pwd(String password){
        super();
        this.password = password;
    }

    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
}
