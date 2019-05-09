package com.hai.jackie.haiproject.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Repeatable(Infos.class)  //Repeatable修饰的Infos是一个可以盛放Info注解的容器
public @interface Info {
    String value();
}
