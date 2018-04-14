package com.example.feign.controller;

import com.example.feign.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CookiesEason
 * 2018/04/14 21:17
 */
@RestController
public class HelloRemoteController {

    @Autowired
    private HelloService helloService;

    @RequestMapping("/hello/{name}")
    public String hello(@PathVariable(value = "name") String name){
        return helloService.hello(name);
    }

}
