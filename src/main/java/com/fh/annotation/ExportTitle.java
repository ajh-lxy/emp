package com.fh.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Lenovo
 * @title: ExportTitle
 * @projectName service_student_web
 * @description: TODO
 * @date 2020/1/1410:44
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExportTitle {
    String title() default "";
}
