package com.boss.learning.service;

import com.boss.learning.dto.ResultDto;
import com.boss.learning.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author Zzwen
 * @date 2020/3/20 9:56
 */
@SpringBootTest
class UserServiceTest {
    @Resource
    private UserService userService;
    @Test
    void saveUserTest(){
        User user = new User();
        user.setUsername("admin");
        user.setPassword("123456");
        user.setSex("男");
        ResultDto dto = userService.saveUser(user);
        System.out.println(dto);
    }
    @Test
    void queryUserTest(){
        ResultDto dto = userService.queryUser("", 1);
        System.out.println(dto);
    }
    @Test
    void updateUserTest(){
        ResultDto userById = userService.findUserById(1);
        User user = (User)userById.getData();
        user.setUsername("root");
        user.setPassword("123456");
        ResultDto dto = userService.updateUser(user);
        System.out.println(dto);
    }
    @Test
    void deleteUserTest(){
        ResultDto dto = userService.deleteUserById(2);
        System.out.println(dto);
    }
}
