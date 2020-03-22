package com.boss.learning.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Zzwen
 * @date 2020/3/20 9:39
 */
@SpringBootTest
class UtilTest {
    @Test
    void passwordTest(){
        String password = "123456";
        String digestPassword = SecurityPasswordUtil.getDigestPassword(password);
        System.out.println(digestPassword);
    }
}
