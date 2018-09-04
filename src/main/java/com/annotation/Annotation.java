package com.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface Annotation {
    public int id() default 1;
    public String msg() default "Hi";
}

