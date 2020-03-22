package com.boss.learning.service;

import com.boss.learning.dto.ResultDto;
import com.boss.learning.entity.Dictionary;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author Zzwen
 * @date 2020/3/19 11:36
 */
@SpringBootTest
class DictionaryServiceTest {

    @Resource
    private DictionaryService dictionaryService;

    @Test
    void queryDictionaryTest(){
        ResultDto dto = dictionaryService.queryDictionary("", 1);
        System.out.println(dto);
    }

    @Test
    void saveDictionaryTest(){
        Dictionary dictionary = new Dictionary();
        dictionary.setParamValue("女");
        dictionary.setRemarks("性别 女");
        dictionary.setStatus(true);
        dictionary.setCreatedTime(new Date());
        dictionary.setCreatedBy("root");
        dictionary.setUpdatedBy("root");
        dictionary.setUpdatedTime(new Date());
        dictionary.setVersion("0.0.1");
        dictionary.setOrganizationId(1);
        dictionary.setDictionaryTypeId(1L);
        ResultDto dto = dictionaryService.saveDictionary(dictionary,null);
        System.out.println(dto);
    }

    @Test
    void deleteDictionaryTest(){
        ResultDto dto = dictionaryService.deleteDictionaryById(2L);
        System.out.println(dto);
    }

    @Test
    void updateDictionaryTest(){
        ResultDto dto = dictionaryService.findDictionaryById(2L);
        Dictionary dictionary = (Dictionary) dto.getData();
        dictionary.setParamValue("女性");
        System.out.println(dictionary);
        ResultDto resultDto = dictionaryService.updateDictionary(dictionary,null);
        System.out.println(resultDto);
    }

}
