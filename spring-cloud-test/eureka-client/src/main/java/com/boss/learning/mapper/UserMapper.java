package com.boss.learning.mapper;

import com.boss.learning.entity.User;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Zzwen
 * @date 2020/3/16 15:08
 */
@Mapper
public interface UserMapper {
    /**
     * 根据id获取用户信息
     * @param userId
     * @return
     */
    User getUserById(@Param("userId") Integer userId);

    /**
     * 保存用户信息
     * @param user
     * @return
     */
    Integer saveUser(User user);

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User findUserByUsername(String username);

    /**
     * 修改用户
     * @param user
     * @return
     */
    Integer updateUser(User user);

    /**
     * 根据姓名查询用户
     * @param username
     * @return
     */
    Page<User> queryUser(String username);

    /**
     * 根据用户id删除用户信息
     * @param userId
     * @return
     */
    Integer deleteUserById(Integer userId);
}
