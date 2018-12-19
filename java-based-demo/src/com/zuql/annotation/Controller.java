package com.zuql.annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解简介：
 * 1)JDK1.5以后特殊的类
 * 2)通常会借助注解对类及其成员进行描述
 * 3)可以将其理解为java中的一种元数据(描述数据的数据)
 *
 * 注解定义：
 * a)需要使用@interface进行定义
 * b)需要借助其它注解进行描述(例如@Target,@Rentention)
 *
 * 注解应用:
 * 1)替换xml对类或其方法的描述
 * 2)通过反射获取注解，然后基于注解的定义执行业务
 *
 * @Target 用于定义你的注解可以修饰谁？
 * @Retention 用于描述我们的注解何时有效
 * @author Administrator
 */
@Retention(RetentionPolicy.RUNTIME)//运行时有效
@Target({ElementType.TYPE})//表示可以类
public @interface Controller {
    String value() default "";

}





