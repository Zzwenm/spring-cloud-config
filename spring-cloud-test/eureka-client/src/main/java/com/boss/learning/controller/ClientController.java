package com.boss.learning.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zzwen
 * @date 2020/3/16 10:07
 */
@RefreshScope
@RestController
public class ClientController {

    @Value("${config.info}")
    private String configInfo;

    @RequestMapping("/configInfo")
    @ResponseBody
    public String configInfo() {
        return configInfo;
    }

}
