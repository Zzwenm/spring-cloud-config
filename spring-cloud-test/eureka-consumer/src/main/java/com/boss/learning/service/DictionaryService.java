package com.boss.learning.service;

import com.boss.learning.dto.ResultDto;
import com.boss.learning.entity.Dictionary;
import com.boss.learning.entity.DictionaryType;
import com.boss.learning.entity.User;
import com.boss.learning.service.impl.DictionaryFallBackImpl;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * 使用Feign调用service方法
 * 默认服务降级方法为DictionaryFallBackImpl
 *
 * @author Zzwen
 * @date 2020/3/17 14:36
 */
@Component
@FeignClient(value = "eureka-client", fallback = DictionaryFallBackImpl.class)
public interface DictionaryService {

    /**
     * 登录
     *
     * @param user
     * @return
     */
    @RequestMapping("/user/login")
    ResultDto login(User user);

    /**
     * 保存用户
     *
     * @param user
     * @return
     */
    @RequestMapping("/user/save")
    ResultDto save(User user);

    /**
     * 根据用户名查询用户
     *
     * @param username
     * @param currentPage
     * @return
     */
    @RequestMapping("/user/query")
    ResultDto queryUser(@RequestParam("username") String username,@RequestParam("currentPage") Integer currentPage);

    /**
     * 根据id删除用户
     *
     * @param userId
     * @return
     */
    @RequestMapping("/user/delete")
    ResultDto deleteUser(@RequestParam("userId") Integer userId);

    /**
     * 根据id查询用户
     *
     * @param id
     * @return
     */
    @RequestMapping("/user/get/{id}")
    ResultDto get(@PathVariable("id") Integer id);

    /**
     * 根据token获取用户信息
     *
     * @param token
     * @return
     */
    @RequestMapping("/user/info")
    ResultDto info(@RequestParam String token);

    /**
     * 保存字典
     *
     * @param dictionary
     * @param request
     * @return
     */
    @PostMapping("/dictionary/save")
    ResultDto saveDictionary(Dictionary dictionary,@RequestParam HttpServletRequest request);

    /**
     * 根据id删除字典
     *
     * @param dictionaryId
     * @return
     */
    @RequestMapping("/dictionary/delete")
    ResultDto deleteDictionary(@RequestParam Long dictionaryId);

    /**
     * 查询字典
     *
     * @param paramValue
     * @param currentPage
     * @return
     */
    @HystrixCommand(fallbackMethod = "fallbackMethod", commandProperties = {
            //开启熔断
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            //请求10次，错误率达到50%则熔断
            //当熔断时间超过5000ms，放出一个请求（半开状态）进行请求，如果成功则关闭熔断，失败则再等待
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
    })
    @GetMapping("/dictionary/query")
    ResultDto queryDictionary(@RequestParam String paramValue,@RequestParam Integer currentPage);

    /**
     * 保存字典类型
     * @param dictionaryType
     * @return
     */
    @PostMapping("/dictionaryType/save")
    ResultDto saveDictionaryType(DictionaryType dictionaryType);

    /**
     * 根据id删除字典类型
     * @param dictionaryTypeId
     * @return
     */
    @RequestMapping("/dictionaryType/delete")
    ResultDto deleteDictionaryType(@RequestParam Long dictionaryTypeId);

    /**
     * 根据字典id以及字典类型查询 字典类型信息
     * @param typeName
     * @param currentPage
     * @return
     */
    @GetMapping("/dictionaryType/query")
    ResultDto queryDictionaryType(@RequestParam String typeName,@RequestParam Integer currentPage);

    /**
     * 发掘测试，查看eureka上的实例信息
     * @return
     */
    @GetMapping("/dictionaryType/discovery")
    Object discovery();
}
