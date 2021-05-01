package com.deo.democlient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${spring.application.name}")
    private String appName;

    @RequestMapping("/test")
    public String test() {
        ServiceInstance choose = loadBalancerClient.choose("demo-server");
        String url = String.format("http://%s:%s/server/echo/%s", choose.getHost(), choose.getPort(), appName);
        System.out.println(url);
        return restTemplate.getForObject(url, String.class);
    }

}
