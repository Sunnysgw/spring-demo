package com.tuling.code;

import java.time.Duration;
import java.time.Instant;

/**
 * 判断一个数是否是2的幂
 */
public class Code1 {

    public static boolean judge1(long input) {
        while (input > 1) {
            if (input % 2 == 1) {
                return false;
            }
            input /= 2;
        }
        return true;
    }

    public static boolean judge2(long input) {
        return (input & (input - 1)) == 0;
    }

    public static void main(String[] args) {
        System.out.println(judge1(123123131313123111L));
        Instant start1 = Instant.now();
        System.out.println(judge1(Long.MAX_VALUE));
        System.out.println(Duration.between(start1, Instant.now()).toNanos());
        Instant start2 = Instant.now();
        System.out.println(judge2(Long.MAX_VALUE));
        System.out.println(Duration.between(start2, Instant.now()).toNanos());
    }

}
