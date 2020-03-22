package com.boss.learning.controller;

import com.boss.learning.dto.ResultDto;
import com.boss.learning.entity.Dictionary;
import com.boss.learning.service.DictionaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Zzwen
 * @date 2020/3/19 11:32
 */
@Slf4j
@ResponseBody
@RestController
@RequestMapping("/dictionary")
public class DictionaryController {

    @Resource
    private DictionaryService dictionaryService;

    @PostMapping("/save")
    public ResultDto saveDictionary(Dictionary dictionary, HttpServletRequest request) {
        return dictionaryService.saveDictionary(dictionary, request);
    }

    @RequestMapping("/delete")
    public ResultDto deleteDictionary(Long dictionaryId) {
        return dictionaryService.deleteDictionaryById(dictionaryId);
    }

    @GetMapping("/query")
    public ResultDto queryDictionary(String paramValue, Integer currentPage) {
        log.info("paramValue : " + paramValue + " ,currentPage : " + currentPage);
        return dictionaryService.queryDictionary(paramValue, currentPage);
    }

}
