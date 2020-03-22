package com.boss.learning.controller;

import com.boss.learning.dto.ResultDto;
import com.boss.learning.entity.DictionaryType;
import com.boss.learning.service.DictionaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Zzwen
 * @date 2020/3/17 8:50
 */
@Slf4j
@RestController
@ResponseBody
@RequestMapping("/dictionaryType")
public class DictionaryTypeController {
    @Resource
    private DictionaryService dictionaryTypeService;
    /**
     * 发现eureka上的服务，用于测试
     */
    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/save")
    public ResultDto saveDictionaryType(DictionaryType dictionaryType) {
        return dictionaryTypeService.saveDictionaryType(dictionaryType);
    }

    @RequestMapping("/delete")
    public ResultDto deleteDictionaryType(Long dictionaryTypeId) {
        return dictionaryTypeService.deleteDictionaryType(dictionaryTypeId);
    }

    @GetMapping("/query")
    public ResultDto queryDictionaryType(String typeName, Integer currentPage) {
        log.info("typeName : " + typeName + " ,currentPage : " + currentPage);
        return dictionaryTypeService.queryDictionaryType(typeName, currentPage);
    }

    @GetMapping("/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("******service : " + service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("EUREKA-CLIENT");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }
        return this.discoveryClient;
    }

}
