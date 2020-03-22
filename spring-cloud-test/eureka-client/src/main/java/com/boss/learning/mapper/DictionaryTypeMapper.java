package com.boss.learning.mapper;

import com.boss.learning.entity.DictionaryType;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

/**
 * 字典管理
 * @author Zzwen
 * @date 2020/3/16 17:39
 */
@Mapper
public interface DictionaryTypeMapper {
    /**
     * 保存
     * @param dictionaryType
     * @return
     */
    int saveDictionaryType(DictionaryType dictionaryType);

    /**
     * 根据id删除
     * @param dictionaryTypeId
     * @return
     */
    int deleteDictionaryTypeById(Long dictionaryTypeId);

    /**
     * 更新
     * @param dictionaryType
     * @return
     */
    int updateDictionaryType(DictionaryType dictionaryType);

    /**
     * 根据id获取
     * @param dictionaryTypeId
     * @return
     */
    DictionaryType getDictionaryTypeById(Long dictionaryTypeId);

    /**
     * 查询所有字典
     * @param typeName
     * @return
     */
    Page<DictionaryType> queryDictionaryType(String typeName);
}
