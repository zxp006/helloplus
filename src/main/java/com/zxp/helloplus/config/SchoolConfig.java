package com.zxp.helloplus.config;

import com.zxp.helloplus.model.SchoolClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zxp
 * @date 2020-12-15 10:23
 * @description:
 */
@ConfigurationProperties(prefix = "school")
@Component
public class SchoolConfig {
    private Map<String, SchoolClass> properties = new HashMap<>();

    public Map<String, SchoolClass> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, SchoolClass> properties) {
        this.properties = properties;
    }
}
