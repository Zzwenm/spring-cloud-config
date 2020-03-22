package com.boss.learning.service;

import com.boss.learning.dto.ResultDto;
import com.boss.learning.entity.User;

/**
 * @author Zzwen
 * @date 2020/3/16 15:07
 */
public interface UserService {
    /**
     * 根据id查找用户
     * @param userId
     * @return
     */
    ResultDto findUserById(Integer userId);

    /**
     * 保存用户
     * @param user
     * @return
     */
    ResultDto saveUser(User user);

    /**
     * 修改用户
     * @param user
     * @return
     */
    ResultDto updateUser(User user);

    /**
     * 登录
     * @param user
     * @return
     */
    ResultDto login(User user);

    /**
     * 根据用户名查询用户
     * @param username
     * @param currentPage
     * @return
     */
    ResultDto queryUser(String username, Integer currentPage);

    /**
     * 根据id删除用户
     * @param userId
     * @return
     */
    ResultDto deleteUserById(Integer userId);
}
