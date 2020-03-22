package com.boss.learning.controller;

import com.boss.learning.dto.ResultDto;
import com.boss.learning.entity.User;
import com.boss.learning.service.UserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Zzwen
 * @date 2020/3/18 17:25
 */
@RestController
@ResponseBody
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/login")
    public ResultDto login(@RequestBody User user) {
        return userService.login(user);
    }

    @RequestMapping("/save")
    public ResultDto save(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @RequestMapping("/query")
    public ResultDto queryUser(String username, Integer currentPage){
        return userService.queryUser(username,currentPage);
    }

    @RequestMapping("/delete")
    public ResultDto deleteUser(Integer userId){
        return userService.deleteUserById(userId);
    }

    @RequestMapping("/get/{id}")
    public ResultDto get(@PathVariable("id") Integer id) {
        return userService.findUserById(id);
    }

    @RequestMapping("/info")
    public ResultDto info(String token){
        Map<String,String> map = new HashMap<>();
        map.put("roles","root");
        map.put("introduction","I am a super administrator");
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        map.put("name","Super Admin");
        return new ResultDto<>(20000,"SUCCESS",map);
    }

}
