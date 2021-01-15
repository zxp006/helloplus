package com.zxp.helloplus.config;

import com.zxp.helloplus.model.param.Common;
import com.zxp.helloplus.model.param.PersonParam;
import com.zxp.helloplus.paramAnnotation.SelfParam;
import com.zxp.helloplus.utils.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zxp
 * @create 2020-09-28 19:28
 */

@Component
@Slf4j
public class MethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(SelfParam.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest servletRequest = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        String parameterName = methodParameter.getParameterName();
        Object object = RequestUtil.getParams(servletRequest);
        String name = servletRequest.getParameter("name");
        log.info("参数名称={},参数值={},{}", parameterName, object);
        SelfParam selfParam = methodParameter.getParameterAnnotation(SelfParam.class);
        Class<?> subClass = null;
        if (selfParam != null) {
            subClass = selfParam.subClass();
        }
        log.info("注解值为:{},{}", subClass, selfParam.required());
        Common<PersonParam> common = new Common<>();
        common.setId(1).setDesc("通用参数");
        PersonParam personParam = new PersonParam();
        personParam.setAge(12).setName("通用用户").setSex((short) 0);
        common.setParam(personParam);
        return common;
    }
}
