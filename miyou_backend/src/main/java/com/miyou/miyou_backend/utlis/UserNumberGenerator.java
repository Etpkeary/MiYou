package com.miyou.miyou_backend.utlis;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * 生成唯一性的用户编号
 * @author LINGLAN
 */
public class UserNumberGenerator {

    /**
     * 存储已使用的编号，用于检查唯一性
     */
    private static final Set<Integer> USERDNUMBERS = new HashSet<>();

    public static int generateRandomNumber() {
        Random rand = new Random();
        //生成0到99999999之间的随机整数
        return rand.nextInt(100000000);
    }

    public static String generateUserNumber() {
        int userNumber = generateRandomNumber();
        //检查是否已使用
        while (USERDNUMBERS.contains(userNumber)) {
            //如果已使用，则重新生成随机数
            userNumber = generateRandomNumber();
        }
        //将新生成的编号添加到已使用的集合中
        USERDNUMBERS.add(userNumber);
        System.out.println("用户编号是：" + userNumber);
        return String.valueOf(userNumber);
    }
}