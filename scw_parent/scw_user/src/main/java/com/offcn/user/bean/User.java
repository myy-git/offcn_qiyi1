package com.offcn.user.bean;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户表模型")
public class User {

    @ApiModelProperty(value = "主键")
    private int id;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "电子邮件")
    private String email;


}
