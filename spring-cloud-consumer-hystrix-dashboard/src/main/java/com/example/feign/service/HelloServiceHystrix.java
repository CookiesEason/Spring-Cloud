package com.example.feign.service;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author CookiesEason
 * 2018/04/15 22:38
 */
@Component
public class HelloServiceHystrix implements HelloService {
    @Override
    public String hello(@RequestParam(value = "name") String name) {
        return "hello " + name + ", this message send failed ";
    }
}
