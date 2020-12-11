package com.offcn.user.exception;

import com.offcn.user.enums.UserExceptionEnum;

public class UserException extends RuntimeException{

    //当异常抛出的时候，会携带我们的信息，然后 记录在日志中
    public UserException(UserExceptionEnum userEnum){
        super(userEnum.getMsg());
    }
}
