package com.fh.utils;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;

/**
 * @author Lenovo
 * @title: Idutil
 * @projectName shop-admin
 * @description: TODO
 * @date 2019/12/416:16
 */
public class Idutil {
    public static String getOrderId(){
        return IdWorker.getIdStr();
    }
}
