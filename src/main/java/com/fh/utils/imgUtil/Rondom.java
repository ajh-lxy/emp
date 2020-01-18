package com.fh.utils.imgUtil;

import java.util.Random;

/**
 * @author Lenovo
 * @title: Rondom
 * @projectName shop_admin_web
 * @description: TODO
 * @date 2019/12/2015:12
 */
public class Rondom {
    /**
     * 产生4位随机数(0000-9999)
     * @return 4位随机数
     */
    /**
     * 生成指定位数的随机数
     * @param length
     * @return
     */
    public static String getRandom(int length){
        String val = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            val += String.valueOf(random.nextInt(10));
        }
        return val;
    }
}
