package com.fh.service;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Priority;

/**
 * @author Lenovo
 * @title: Log4jMyAppender
 * @projectName service_student_web
 * @description: TODO
 * @date 2020/1/1322:39
 */
public class Log4jMyAppender extends DailyRollingFileAppender {
    @Override
    public boolean isAsSevereAsThreshold(Priority priority) {
        //只判断是否相等，而不判断优先级
        return this.getThreshold().equals(priority);
    }
}
