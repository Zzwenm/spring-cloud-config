package com.boss.learning.service;

import com.boss.learning.dto.ResultDto;
import com.boss.learning.entity.DictionaryType;

/**
 * @author Zzwen
 * @date 2020/3/16 18:28
 */
public interface DictionaryTypeService {

    /**
     * 查询所有类型信息
     * @param typeName
     * @param currentPage
     * @return
     */
    ResultDto queryDictionaryType(String typeName, Integer currentPage);

    /**
     * 保存新增字典
     * @param dictionaryType
     * @return
     */
    ResultDto saveDictionaryType(DictionaryType dictionaryType);

    /**
     * 根据id删除
     * @param dictionaryTypeId
     * @return
     */
    ResultDto deleteDictionaryTypeById(Long dictionaryTypeId);

    /**
     * 更新字典
     * @param dictionaryType
     * @return
     */
    ResultDto updateDictionaryType(DictionaryType dictionaryType);

}
