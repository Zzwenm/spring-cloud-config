package com.boss.learning;

import com.boss.learning.entity.User;
import com.boss.learning.mapper.DictionaryTypeMapper;
import com.boss.learning.service.DictionaryTypeService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.Cacheable;

import javax.annotation.Resource;
import java.time.ZonedDateTime;

@SpringBootTest
class EurekaClientApplicationTests {

    @Resource
    private DictionaryTypeMapper dictionaryTypeMapper;
    @Resource
    private DictionaryTypeService dictionaryTypeService;

    @Test
    void dictionaryMapperTest() {
        ZonedDateTime time = ZonedDateTime.now();
        System.out.println(time);
    }

    @Cacheable(value = {"valueName"}, key = "'keyName1'")
    public User findUser() {
        System.out.println("执行方法...");
        return new User(1,"name","","nan");
    }

}
