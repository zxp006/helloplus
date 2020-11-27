package com.zxp.helloplus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zxp
 * @date 2020-11-23 13:52
 * @description:
 */

@RestController
public class HttpRequestController {
    @Autowired
    RestTemplate restTemplate;
    @PostMapping("/post")
    public String postObj(){
        String url="http://localhost:8081/good3?id={1}";
        MultiValueMap<String,Object> map=new LinkedMultiValueMap<>();
        map.add("goodNo","1000");
        map.add("goodName","手机2");
        map.add("goodPrice",20);
        Map<String,Object> map2 = new HashMap<>();
        map2.put("goodNo","1000");
        map2.put("goodName","手机");
        map2.put("goodPrice",20);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, map, String.class,100);
        return  responseEntity.getBody();
    }
}
