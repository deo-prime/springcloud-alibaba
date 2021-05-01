package com.deo.demoservice.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/server")
public class ServerController {

    @RequestMapping("/echo/{text}")
    public String echo(@PathVariable String text) {
        return "server receive: " + text;
    }
}
