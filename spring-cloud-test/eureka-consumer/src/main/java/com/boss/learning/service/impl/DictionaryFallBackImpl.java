package com.boss.learning.service.impl;

import com.boss.learning.dto.ResultDto;
import com.boss.learning.entity.Dictionary;
import com.boss.learning.entity.DictionaryType;
import com.boss.learning.entity.User;
import com.boss.learning.service.DictionaryService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 数据字典服务降级
 *
 * @author Zzwen
 * @date 2020/3/17 19:37
 */
@Component
public class DictionaryFallBackImpl implements DictionaryService {
    @Override
    public ResultDto login(User user) {
        return new ResultDto<>(20000, "服务器繁忙，请稍后再试！", null);
    }

    @Override
    public ResultDto save(User user) {
        return new ResultDto<>(20000, "服务器繁忙，请稍后再试！", null);
    }

    @Override
    public ResultDto queryUser(String username, Integer currentPage) {
        return new ResultDto<>(20000, "服务器繁忙，请稍后再试！", null);
    }

    @Override
    public ResultDto deleteUser(Integer userId) {
        return new ResultDto<>(20000, "服务器繁忙，请稍后再试！", null);
    }

    @Override
    public ResultDto get(Integer id) {
        return new ResultDto<>(20000, "服务器繁忙，请稍后再试！", null);
    }

    @Override
    public ResultDto info(String token) {
        return new ResultDto<>(20000, "服务器繁忙，请稍后再试！", null);
    }

    @Override
    public ResultDto saveDictionary(Dictionary dictionary, HttpServletRequest request) {
        return new ResultDto<>(20000, "服务器繁忙，请稍后再试！", null);
    }

    @Override
    public ResultDto deleteDictionary(Long dictionaryId) {
        return new ResultDto<>(20000, "服务器繁忙，请稍后再试！", null);
    }

    @Override
    public ResultDto queryDictionary(String paramValue, Integer currentPage) {
        return new ResultDto<>(20000, "服务器繁忙，请稍后再试！", null);
    }

    @Override
    public ResultDto saveDictionaryType(DictionaryType dictionaryType) {
        return new ResultDto<>(20000, "服务器繁忙，请稍后再试！", null);
    }

    @Override
    public ResultDto deleteDictionaryType(Long dictionaryTypeId) {
        return new ResultDto<>(20000, "服务器繁忙，请稍后再试！", null);
    }

    @Override
    public ResultDto queryDictionaryType(String typeName, Integer currentPage) {
        return new ResultDto<>(20000, "服务器繁忙，请稍后再试！", null);
    }

    @Override
    public Object discovery() {
        return new ResultDto<>(20000, "服务器繁忙，请稍后再试！", null);
    }
}
