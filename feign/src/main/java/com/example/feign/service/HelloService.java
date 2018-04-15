package com.example.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author CookiesEason
 * 2018/04/14 21:15
 */
@FeignClient(name = "spring-cloud-producer",fallback = HelloServiceHystrix.class)
public interface HelloService {
    @RequestMapping(value = "/hello")
    public String hello(@RequestParam(value = "name") String name);
}
