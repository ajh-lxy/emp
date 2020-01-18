package com.fh.annotation;

/**
 * @author Lenovo
 * @title: ExportExcel
 * @projectName service_student_web
 * @description: TODO
 * @date 2020/1/1322:53
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExportExcel {
    String name() default "";
}

