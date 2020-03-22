package com.boss.learning.service.impl;

import com.boss.learning.dto.ResultDto;
import com.boss.learning.entity.User;
import com.boss.learning.mapper.UserMapper;
import com.boss.learning.service.UserService;
import com.boss.learning.util.JwtUtil;
import com.boss.learning.util.SecurityPasswordUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sun.security.provider.MD5;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Zzwen
 * @date 2020/3/16 15:08
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public ResultDto findUserById(Integer userId) {
        User user = userMapper.getUserById(userId);
        if (user != null) {
            return new ResultDto<>(20000, "查询成功！", user);
        } else {
            return new ResultDto<>(444, "用户不存在！id=", userId);
        }
    }

    @Override
    public ResultDto saveUser(User user) {
        if(user.getUserId()!=null){
            return updateUser(user);
        }
        String digestPassword = SecurityPasswordUtil.getDigestPassword(user.getPassword());
        user.setPassword(digestPassword);
        Integer i = userMapper.saveUser(user);
        log.info("*******保存的user：" + user);
        if (i > 0) {
            return new ResultDto<>(20000, "保存成功", i);
        } else {
            return new ResultDto<>(444, "保存失败", null);
        }
    }

    @Override
    public ResultDto updateUser(User user){
        log.info("********更改用户信息*********");
        log.info("用户：" + user);
        String digestPassword = SecurityPasswordUtil.getDigestPassword(user.getPassword());
        user.setPassword(digestPassword);
        Integer i = userMapper.updateUser(user);
        log.info("影响行数：" + i);
        if(i>0){
            return new ResultDto<>(20000,"修改成功",i);
        }
        return new ResultDto<>(444,"修改失败",i);
    }

    @Override
    public ResultDto login(User user) {
        log.info("********用户登录*********");
        User findUser = userMapper.findUserByUsername(user.getUsername());
        log.info("查找结果：" + findUser);
        //获取加密码
        String digestPassword = SecurityPasswordUtil.getDigestPassword(user.getPassword());
        if (findUser == null || !digestPassword.equals(findUser.getPassword())) {
            return new ResultDto<>(400, "用户不存在或者密码不正确", null);
        }
        String token = JwtUtil.createJwt(findUser);
        Map<String,String> map =new HashMap<>(1);
        map.put("token",token);
        return new ResultDto<>(20000,"登陆成功",map);
    }

    @Override
    public ResultDto queryUser(String username, Integer currentPage) {
        log.info("********查询用户信息*********");
        //分页处理
        PageHelper.startPage(currentPage, 10);
        Page<User> userList = userMapper.queryUser(username);
        log.info(userList.toString());
        //获取页数
        Long pages = userList.getTotal();
        Map<String, Object> map = new HashMap<>(2);
        map.put("user", userList);
        map.put("page", pages);
        ResultDto<Map> dto = new ResultDto<>(20000, "查询成功！");
        dto.setData(map);
        return dto;
    }

    @Override
    public ResultDto deleteUserById(Integer userId) {
        log.info("*********删除用户信息**********");
        Integer i = userMapper.deleteUserById(userId);
        log.info("影响行数：" + i);
        if(i>0){
            return new ResultDto<>(20000,"删除成功",i);
        }
        return new ResultDto<>(444,"删除失败",i);
    }
}
