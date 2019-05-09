package com.hai.jackie.annotationlib;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE,ElementType.FIELD,ElementType.METHOD})
public @interface BiaoJi {
    String value();
}
