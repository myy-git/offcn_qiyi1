package com.offcn.user.service.impl;


import com.offcn.user.pojo.*;

public interface UserService {

    //用户注册
    public void registerUser(TMember member );

    //用户登录
    public TMember login(String username,String password);


}
