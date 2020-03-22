package com.boss.learning.controller;

import com.boss.learning.dto.ResultDto;
import com.boss.learning.entity.User;
import com.boss.learning.service.DictionaryService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Zzwen
 * @date 2020/3/18 17:25
 */
@RestController
@ResponseBody
@RequestMapping("/user")
public class UserController {

    @Resource
    private DictionaryService userService;

    @RequestMapping("/login")
    public ResultDto login(@RequestBody User user) {
        return userService.login(user);
    }

    @RequestMapping("/save")
    public ResultDto save(User user) {
        return userService.save(user);
    }

    @RequestMapping("/query")
    public ResultDto queryUser(String username, Integer currentPage) {
        return userService.queryUser(username, currentPage);
    }

    @RequestMapping("/delete")
    public ResultDto deleteUser(Integer userId) {
        return userService.deleteUser(userId);
    }

    @RequestMapping("/info")
    public ResultDto info(String token) {
        return userService.info(token);
    }

}
