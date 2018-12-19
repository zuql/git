package com.zuql.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by 沧澜 on 2018/11/7.
 */
@Retention(RetentionPolicy.RUNTIME)//运行时有效
@Target({ElementType.TYPE,ElementType.METHOD})//表示可以类
public @interface RequestMapping {
    String[] value() default "";
}
