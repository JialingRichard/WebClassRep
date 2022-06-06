package com.example.restapi4.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("用户实体类注释")
public class MyUser {


    @ApiModelProperty("userName注释")
    private String userName="adminName";


    @ApiModelProperty("passwd注释")
    private String passWard="adminPasswd";

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWard() {
        return passWard;
    }

    public void setPassWard(String passWard) {
        this.passWard = passWard;
    }
}
