package com.zxp.helloplus.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author zxp
 * @create 2020-09-29 10:36
 */

@Configuration
public class WebConfiger  implements WebMvcConfigurer{

    @Autowired
    private MethodArgumentResolver methodArgumentResolver;


    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(methodArgumentResolver);
    }
}
