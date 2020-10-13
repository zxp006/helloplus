package com.zxp.helloplus.paramAnnotation;

import java.lang.annotation.*;

/**
 * @author zxp
 * @create 2020-09-28 19:15
 */

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SelfParam {
    String value() default "注入参数";

    boolean required() default true;
}
