package com.zxp.helloplus.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.entity.ContentType;
import org.springframework.http.HttpMethod;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author zxp
 * @create 2020-09-22 14:42
 */

@Slf4j
public class RequestUtil {

    /**
     * 读取请求体参数
     *
     * @param servletRequest
     * @return
     */
    public static String getBody(HttpServletRequest servletRequest) {
        try {
            BufferedReader reader = servletRequest.getReader();
            StringBuilder sb = new StringBuilder();
            char[] buf = new char[1024];
            int rd;
            while ((rd = reader.read(buf)) != -1) {
                sb.append(buf, 0, rd);
            }
            String parameter = sb.toString();
            parameter = URLDecoder.decode(parameter, "UTF-8");
            return parameter;
        } catch (IOException var7) {
            throw new RuntimeException(var7);
        }
    }

    public static Map getParameters(HttpServletRequest servletRequest) {
        Map<String, String[]> map = servletRequest.getParameterMap();
        Map<String, String> paramMap = new HashMap(16);
        Iterator entries = map.entrySet().iterator();

        while (entries.hasNext()) {
            Map.Entry<String, String[]> entry = (Map.Entry) entries.next();
            if (entry.getValue() != null && (entry.getValue()).length > 0) {
                paramMap.put(entry.getKey(), (entry.getValue())[0]);
            }
        }
        return paramMap;
    }

    public static Object getParams(HttpServletRequest servletRequest) {
        String contentType = servletRequest.getContentType();
        log.info("当前请求ContentType={},要验证的ContentType={}", contentType, ContentType.MULTIPART_FORM_DATA.getMimeType());
        if (servletRequest.getMethod().equals(HttpMethod.POST.name())) {
            //multipart/form-data 不支持流读取参数
            if (StringUtils.isNotBlank(contentType) && contentType.startsWith(ContentType.MULTIPART_FORM_DATA.getMimeType())) {
                return getParameters(servletRequest);
            } else {
                if (StringUtils.isNotBlank(contentType) && contentType.startsWith(ContentType.APPLICATION_FORM_URLENCODED.getMimeType())) {
                    //application/x-www-form-urlencoded  同时支持流和getParam读取参数
                    return  getParameters(servletRequest);
//                    return  getBody(servletRequest); //id=2&desc=统一form-urlencoded&param={"name":"梦雪","sex":1,"age":20}
                } else {
                    //application/json
                    return getBody(servletRequest);
                }
            }
        } else {
            return getParameters(servletRequest);
        }
    }
}
