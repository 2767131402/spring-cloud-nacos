package com.microservice.cloud.controller;

import com.microservice.cloud.config.RestTemplateConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description : nacos服务消费控制类
 * @Author : zhenglei
 * @Date: 2020-08-22 10:22
 */
@RestController
@RefreshScope
public class TestHelloController {
    @Value("${name:admin}")
    private String name;

    @Resource
    private RestTemplateConfig restTemplateConfig;

    @RequestMapping(value = "/echo/{str}", method = RequestMethod.GET)
    public String echo(@PathVariable String str) {
        str = name;
        return restTemplateConfig.restTemplate().getForObject("http://nacos-service-provider/echo/" + str, String.class);
    }

}
