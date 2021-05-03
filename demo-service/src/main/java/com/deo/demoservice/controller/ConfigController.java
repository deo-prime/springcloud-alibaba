package com.deo.demoservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
@RequestMapping("/config")
public class ConfigController {

    @Value("${avengers.name}")
    private String name;

    @Value("${avengers.weapon}")
    private String weapon;

    @RequestMapping("/get")
    public String get() {
        return String.format("name :%s, weapon: %s", name, weapon);
    }
}
