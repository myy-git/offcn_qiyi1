package com.offcn.user.service.impl;

import com.offcn.dycommon.response.AppResponse;
import com.offcn.user.enums.UserExceptionEnum;
import com.offcn.user.exception.UserException;
import com.offcn.user.mapper.TMemberMapper;
import com.offcn.user.pojo.TMember;
import com.offcn.user.pojo.TMemberExample;
import com.offcn.user.vo.resp.UserRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TMemberMapper tMemberMapper;

    @Override
    public void registerUser(TMember member) {
        //查询手机号是否已经注册过
        TMemberExample ex = new TMemberExample();
        ex.createCriteria().andLoginacctEqualTo(member.getLoginacct());
        long l = tMemberMapper.countByExample(ex);
        if (l>0){
            throw new UserException(UserExceptionEnum.LOGINACCT_EXIST);
        }else{
            //进行密码加密
            BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
            String password = bCrypt.encode(member.getUserpswd());
            member.setUserpswd(password);
            member.setUsername(member.getLoginacct());

            //实名认证状态 0 - 未实名认证， 1 - 实名认证申请中， 2 - 已实名认证
            member.setAuthstatus("0");
            //用户类型: 0 - 个人， 1 - 企业
            member.setUsertype("0");
            //账户类型: 0 - 企业， 1 - 个体， 2 - 个人， 3 - 政府
            member.setAccttype("2");

            System.out.println("插入数据:" + member.getLoginacct());

            tMemberMapper.insert(member);
        }
    }

    @Override
    public TMember login(String username, String password) {
        TMemberExample ex = new TMemberExample();
        ex.createCriteria().andLoginacctEqualTo(username);
        List<TMember> tMembers = tMemberMapper.selectByExample(ex);
        //判断是否有这个用户名
        if (tMembers.size()>0){
            TMember tMember = tMembers.get(0);
            BCryptPasswordEncoder bceyp = new BCryptPasswordEncoder();
            boolean matches = bceyp.matches(password, tMember.getUserpswd());
            //判断密码是否正确
            return matches ?  tMember : null;
        }
        return null;
    }



}
