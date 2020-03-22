package com.boss.learning.service.impl;

import com.boss.learning.dto.ResultDto;
import com.boss.learning.entity.DictionaryType;
import com.boss.learning.mapper.DictionaryTypeMapper;
import com.boss.learning.service.DictionaryTypeService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Zzwen
 * @date 2020/3/16 18:29
 */
@Slf4j
@Service
public class DictionaryTypeServiceImpl implements DictionaryTypeService {

    @Resource
    private DictionaryTypeMapper dictionaryTypeMapper;

    @Override
    @Cacheable(value = "dictionaryTypeCache", key = "#typeName + #currentPage")
    public ResultDto<Map> queryDictionaryType(String typeName, Integer currentPage) {
        log.info("*******查询字典类型信息*******");
        //分页处理
        PageHelper.startPage(currentPage, 10);
        Page<DictionaryType> dictionaryTypeList = dictionaryTypeMapper.queryDictionaryType(typeName);
        log.info(dictionaryTypeList.toString());
        //获取页数
        Long pages = dictionaryTypeList.getTotal();
        Map<String, Object> map = new HashMap<>(2);
        map.put("dictionaryType", dictionaryTypeList);
        map.put("page", pages);
        ResultDto<Map> dto = new ResultDto<>(20000, "查询成功！");
        dto.setData(map);
        return dto;
    }

    @Override
    @CachePut(value = "dictionaryTypeCache", key = "#dictionaryType.dictionaryTypeId")
    @CacheEvict(value = "dictionaryTypeCache", allEntries = true)
    public ResultDto saveDictionaryType(DictionaryType dictionaryType) {
        if(dictionaryType.getDictionaryTypeId()!=null){
            return updateDictionaryType(dictionaryType);
        }
        log.info("*******保存字典类型********");
        int i = dictionaryTypeMapper.saveDictionaryType(dictionaryType);
        log.info("影响行数：" + i);
        if (i > 0) {
            return new ResultDto<>(20000, "保存成功！", i);
        } else {
            return new ResultDto<>(444, "保存失败！", i);
        }
    }

    @Override
    @CacheEvict(value = "dictionaryTypeCache", allEntries = true)
    public ResultDto deleteDictionaryTypeById(Long dictionaryTypeId) {
        log.info("*******删除字典类型*******");
        int i = dictionaryTypeMapper.deleteDictionaryTypeById(dictionaryTypeId);
        log.info("影响行数：" + i);
        if (i > 0) {
            return new ResultDto<>(20000, "删除成功！", i);
        } else {
            return new ResultDto<>(444, "删除失败！", i);
        }
    }

    @Override
    @CacheEvict(value = "dictionaryTypeCache", allEntries = true)
    public ResultDto updateDictionaryType(DictionaryType dictionaryType) {
        log.info("******更新字典类型******");
        int i = dictionaryTypeMapper.updateDictionaryType(dictionaryType);
        log.info("影响行数：" + i);
        if (i > 0) {
            return new ResultDto<>(20000, "修改成功！", i);
        } else {
            return new ResultDto<>(444, "修改失败！", i);
        }
    }


    /**
     * 熔断测试
     *
     * @return
     */
    @HystrixCommand(fallbackMethod = "fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "1000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),
    })
    public String timeout() {
        return "timeout";
    }

    public String fallback() {
        return "fallback";
    }

}
