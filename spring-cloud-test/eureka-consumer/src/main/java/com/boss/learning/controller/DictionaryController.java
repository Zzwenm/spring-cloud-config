package com.boss.learning.controller;

import com.boss.learning.dto.ResultDto;
import com.boss.learning.entity.Dictionary;
import com.boss.learning.entity.DictionaryType;
import com.boss.learning.service.DictionaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * @author Zzwen
 * @date 2020/3/16 21:25
 */
@Slf4j
@RestController
@ResponseBody
@RequestMapping("/dictionary")
public class DictionaryController {

    public static final String URL = "http://localhost:8762";

    @Resource
    private DictionaryService dictionaryService;

    @Value("${server.port}")
    private String serverport;

    @PostMapping("/save")
    public ResultDto saveDictionary(Dictionary dictionary, HttpServletRequest request) {
        log.info("******consumer******");
        return dictionaryService.saveDictionary(dictionary,request);
    }

    @RequestMapping("/delete")
    public ResultDto deleteDictionary(@RequestParam("dictionaryId") Long dictionaryId) {
        log.info("******consumer******");
        return dictionaryService.deleteDictionary(dictionaryId);
    }

    @RequestMapping("/query")
    public ResultDto queryDictionary(String paramValue, Integer currentPage) {
        log.info("******consumer******");
        return dictionaryService.queryDictionary(paramValue,currentPage);
    }

    @RequestMapping("/timeout")
    public String timeout(){
        try{
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        //默认等待时间为1s，故意超时，将会有超时报错
        return serverport;
    }

}
