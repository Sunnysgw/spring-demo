package com.hihonor.demo;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class StringBufferDemo {

    public static void main(String[] args) throws InterruptedException {
        String str1 = new StringBuilder("hello").append("world").toString();
        System.out.println(str1 == str1.intern());

        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2 == str2.intern());

        String str3 = new StringBuilder("dev").toString();
        System.out.println(str3 == str3.intern());
    }

}
