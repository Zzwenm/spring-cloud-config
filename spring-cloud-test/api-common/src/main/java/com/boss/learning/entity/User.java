package com.boss.learning.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Zzwen
 * @date 2020/3/16 12:02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private Integer userId;
    private String username;
    private String password;
    private String sex;
}
