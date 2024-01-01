package com.aisser.annotation;

import java.lang.annotation.*;

/**
 * 标记该方法
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OptLog {

    String optType() default "";
}
