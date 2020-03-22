package com.boss.learning.service;

import com.boss.learning.dto.ResultDto;
import com.boss.learning.entity.Dictionary;
import com.boss.learning.entity.DictionaryType;

import javax.servlet.http.HttpServletRequest;

/**
 * 数据字典逻辑层
 * @author Zzwen
 * @date 2020/3/16 18:28
 */
public interface DictionaryService {
    /**
     * 根据参数值查询所有字典
     * @param paramValue
     * @param currentPage
     * @return
     */
    ResultDto queryDictionary(String paramValue, Integer currentPage);

    /**
     * 保存新增字典
     * @param dictionary
     * @param request
     * @return
     */
    ResultDto saveDictionary(Dictionary dictionary, HttpServletRequest request);

    /**
     * 根据id删除
     * @param dictionaryId
     * @return
     */
    ResultDto deleteDictionaryById(Long dictionaryId);

    /**
     * 更新字典
     * @param dictionary
     * @param request
     * @return
     */
    ResultDto updateDictionary(Dictionary dictionary, HttpServletRequest request);

    /**
     * 根据id查询字典
     * @param dictionaryId
     * @return
     */
    ResultDto findDictionaryById(long dictionaryId);
}
