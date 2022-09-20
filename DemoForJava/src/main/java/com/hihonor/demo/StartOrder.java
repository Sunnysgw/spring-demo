package com.hihonor.demo;


public class StartOrder {

    static String a = "hello";

    String b = "world";

    static {
        System.out.println("静态代码块");
    }

    {
        System.out.println("构造代码块");
    }

    public StartOrder() {
        System.out.println("构造方法");
    }

    public static void main(String[] args) {
        StartOrder demo = new StartOrder();
    }

}
