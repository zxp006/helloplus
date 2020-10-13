package com.zxp.helloplus.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zxp.helloplus.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Max;
import java.util.Date;

@RestController
@Validated
public class ValidatestController {

    @Autowired
    HttpServletResponse response;
    @GetMapping("/p")
    public void    getPerson(@Max(value = 100,message = "年龄不能大于100")@RequestParam("age") String age ) throws  Exception {
        ObjectMapper mapper = new ObjectMapper();
        Person person=new Person(1,"哈哈",20,"书法","画家",10000d,new Date(),new Date());
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getWriter(),person);
    }
}