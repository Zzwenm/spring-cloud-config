package com.boss.learning.mapper;

import com.boss.learning.entity.Dictionary;
import com.boss.learning.entity.DictionaryType;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

/**
 * 职位管理
 * @author Zzwen
 * @date 2020/3/16 17:45
 */
@Mapper
public interface DictionaryMapper {

    /**
     * 根据参数值查询字典
     * @param paramValue
     * @return
     */
    Page<DictionaryType> queryDictionary(String paramValue);

    /**
     * 保存字典
     * @param dictionary
     * @return
     */
    int saveDictionary(Dictionary dictionary);

    /**
     * 根据id删除字典
     * @param dictionaryId
     * @return
     */
    int deleteDictionaryById(Long dictionaryId);

    /**
     * 更新字典
     * @param dictionary
     * @return
     */
    int updateDictionary(Dictionary dictionary);

    /**
     * 根据id查询字典
     * @param dictionaryId
     * @return
     */
    Dictionary findDictionaryById(Long dictionaryId);
}
