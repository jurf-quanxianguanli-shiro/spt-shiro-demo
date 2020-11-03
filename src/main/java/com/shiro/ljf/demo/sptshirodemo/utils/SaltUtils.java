package com.shiro.ljf.demo.sptshirodemo.utils;

import java.util.Random;

/**
 * @ClassName: SaltUtils
 * @Description: TODO
 * @Author: liujianfu
 * @Date: 2020/10/29 22:53:08 
 * @Version: V1.0
 **/
public class SaltUtils {
    /**
     * 生成salt的静态方法
     * @param n
     * @return
     */
    public static String getSalt(int n){
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz01234567890!@#$%^&*()".toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char aChar = chars[new Random().nextInt(chars.length)];
            sb.append(aChar);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String salt = getSalt(8);
        System.out.println(salt);
    }
}
