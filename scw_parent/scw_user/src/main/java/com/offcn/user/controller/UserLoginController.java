package com.offcn.user.controller;


import com.offcn.dycommon.response.AppResponse;
import com.offcn.user.bean.User;
import com.offcn.user.component.SmsTemplate;
import com.offcn.user.pojo.TMember;
import com.offcn.user.service.impl.UserService;
import com.offcn.user.vo.req.UserRegistVo;
import com.offcn.user.vo.resp.UserRespVo;
import io.swagger.annotations.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

// 替换类中
// private final Logger logger = LoggerFactory.getLogger(当前类名.class);
@Slf4j

@RestController
@RequestMapping("/user")
@Api(tags = "用户登录/注册模块")
public class UserLoginController {

    @Autowired
    private SmsTemplate smsTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @ApiOperation("获取验证码")
    @ApiImplicitParam(name = "phone",value = "手机号",required = true)
    @PostMapping("/sendCode")
    public AppResponse<Object> sendCode(String phone){
        //1.随机生成验证码
        String code = UUID.randomUUID().toString().substring(0, 6);
        System.out.println(code);
        //2.将随机生成的验证码和手机号存储在redis中
        redisTemplate.opsForValue().set(phone,code);
        //3.发送验证码
        String result = smsTemplate.sendCode(phone, code);
        if (!result.equals("fail")  ){
            return AppResponse.ok(result);
        }else{
            return AppResponse.fail("验证码发送失败");
        }
    }

    @ApiOperation("用户注册模块")
    @PostMapping("/regist")
    public AppResponse<String> regist(UserRegistVo user){
        //查询出验证码
        String code = redisTemplate.opsForValue().get(user.getLoginacct());
        //判断验证码是否存在 不存在是没有这个手机号
        if (code == null){
            return AppResponse.fail("验证码不存在");
        }
        //判断验证码是否正确
        else{
            if (code.equals(user.getCode())){

                TMember tMember = new TMember();
                //复制user里的共有属性的值赋给tMember
                BeanUtils.copyProperties(user,tMember);
                //添加到数据库
                userService.registerUser(tMember);

                return AppResponse.ok("注册成功");
            }
            //不正确
            else{
                return AppResponse.fail("验证码错误");
            }
        }
    }


    @ApiOperation("用户登录")
    @ApiImplicitParams(value ={
            @ApiImplicitParam(name = "username",value = "用户名",required = true),
            @ApiImplicitParam(name = "password",value = "密码",required = true)})
    @PostMapping("/login")
    public AppResponse<UserRespVo> login(String username,String password){
        TMember login = userService.login(username, password);
        if (login == null){
            AppResponse<UserRespVo> fail = AppResponse.fail(null);
            fail.setMsg("密码输入错误");
            return fail;
        }else{
            //将数据返回到页面
            UserRespVo userRespVo = new UserRespVo();

            String token = UUID.randomUUID().toString().replace("-", "");
            userRespVo.setAccessToken(token);
            BeanUtils.copyProperties(login,userRespVo);
            //将login中的数据存储在redis缓冲半个小时失效 这是正确的 为了测试 永久不失效
            //redisTemplate.opsForValue().set(token,login.getId()+"",30, TimeUnit.MINUTES);
            redisTemplate.opsForValue().set(token,login.getId()+"");
            return AppResponse.ok(userRespVo);
        }

    }






}
