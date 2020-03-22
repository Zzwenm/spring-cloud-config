package com.boss.learning.service.impl;

import com.boss.learning.dto.ResultDto;
import com.boss.learning.entity.Dictionary;
import com.boss.learning.entity.DictionaryType;
import com.boss.learning.mapper.DictionaryMapper;
import com.boss.learning.service.DictionaryService;
import com.boss.learning.util.JwtUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Zzwen
 * @date 2020/3/16 18:28
 */
@Slf4j
@Service
public class DictionaryServiceImpl implements DictionaryService {

    @Resource
    private DictionaryMapper dictionaryMapper;

    @Override
    @Cacheable(value = "dictionaryCache", key = "#paramValue + #currentPage")
    public ResultDto queryDictionary(String paramValue, Integer currentPage) {
        log.info("*******查询字典*******");
        //分页处理
        PageHelper.startPage(currentPage, 10);
        Page<DictionaryType> dictionaryList = dictionaryMapper.queryDictionary(paramValue);
        log.info(dictionaryList.toString());
        //获取页数
        Long pages = dictionaryList.getTotal();
        Map<String, Object> map = new HashMap<>(2);
        map.put("dictionary", dictionaryList);
        map.put("page", pages);
        ResultDto<Map> dto = new ResultDto<>(20000, "查询成功！");
        dto.setData(map);
        return dto;
    }

    @Override
    @CachePut(value = "dictionaryCache", key = "#dictionary.dictionaryId")
    @CacheEvict(value = "dictionaryCache", allEntries = true)
    public ResultDto saveDictionary(Dictionary dictionary, HttpServletRequest request) {
        if(dictionary.getDictionaryId()!=null){
            return updateDictionary(dictionary,request);
        }
        log.info("*******保存字典********");
        String token = request.getHeader("X-Token");
        Claims claims = JwtUtil.parseJwt(token);
        String username = (String)claims.get("username");
        dictionary.setCreatedBy(username);
        dictionary.setCreatedTime(new Date());
        dictionary.setUpdatedBy(username);
        dictionary.setUpdatedTime(new Date());
        int i = dictionaryMapper.saveDictionary(dictionary);
        log.info("影响行数：" + i);
        if (i > 0) {
            return new ResultDto<>(20000, "保存成功！", i);
        } else {
            return new ResultDto<>(444, "保存失败！", i);
        }
    }

    @Override
    @CacheEvict(value = "dictionaryCache", allEntries = true)
    public ResultDto deleteDictionaryById(Long dictionaryId) {
        log.info("*******删除字典*******");
        int i = dictionaryMapper.deleteDictionaryById(dictionaryId);
        log.info("影响行数：" + i);
        if (i > 0) {
            return new ResultDto<>(20000, "删除成功！", i);
        } else {
            return new ResultDto<>(444, "删除失败！", i);
        }
    }

    @Override
    @CacheEvict(value = "dictionaryCache", allEntries = true)
    public ResultDto updateDictionary(Dictionary dictionary, HttpServletRequest request) {
        log.info("******更新字典******");
        String token = request.getHeader("X-Token");
        Claims claims = JwtUtil.parseJwt(token);
        String username = (String)claims.get("username");
        dictionary.setUpdatedBy(username);
        dictionary.setUpdatedTime(new Date());
        int i = dictionaryMapper.updateDictionary(dictionary);
        log.info("影响行数：" + i);
        if (i > 0) {
            return new ResultDto<>(20000, "修改成功！", i);
        } else {
            return new ResultDto<>(444, "修改失败！", i);
        }
    }

    @Override
    @Cacheable(value = "dictionaryCache", key = "#dictionaryId")
    public ResultDto findDictionaryById(long dictionaryId) {
        log.info("******根据id查询字典******");
        Dictionary dictionary = dictionaryMapper.findDictionaryById(dictionaryId);
        log.info("查询结果：" + dictionary);
        return new ResultDto<>(20000, "SUCCESS", dictionary);
    }
}
