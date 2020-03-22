package com.boss.learning.service;

import com.boss.learning.dto.ResultDto;
import com.boss.learning.entity.DictionaryType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author Zzwen
 * @date 2020/3/19 13:58
 */
@SpringBootTest
class DictionaryTypeServiceTest {
    @Resource
    private DictionaryTypeService dictionaryTypeService;

    @Test
    void queryDictionaryType(){
        ResultDto dto = dictionaryTypeService.queryDictionaryType("", 1);
        System.out.println(dto);
    }

    @Test
    void saveDictionaryTypeTest(){
        DictionaryType dictionaryType = new DictionaryType();
        dictionaryType.setTypeName("type_test");
        dictionaryType.setRemarks("测试数据");
        ResultDto dto = dictionaryTypeService.saveDictionaryType(dictionaryType);
        System.out.println(dto);
    }

    @Test
    void updateDictionaryTypeTest(){
        DictionaryType dictionaryType = new DictionaryType();
        dictionaryType.setDictionaryTypeId(15L);
        dictionaryType.setRemarks("修改");
        dictionaryType.setTypeName("修改数据");
        ResultDto dto = dictionaryTypeService.updateDictionaryType(dictionaryType);
        System.out.println(dto);
    }

    @Test
    void deleteDictionaryTypeTest(){
        ResultDto dto = dictionaryTypeService.deleteDictionaryTypeById(15L);
        System.out.println(dto);
    }
}
