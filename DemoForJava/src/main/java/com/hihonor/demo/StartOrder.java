package com.hihonor.demo;


public class StartOrder {

    static String a = "hello";

    String b = "world";

    static {
        System.out.println("father静态代码块");
    }

    {
        System.out.println("father构造代码块");
    }

    public StartOrder() {
        System.out.println("father构造方法");
    }

    private static class Child extends StartOrder {
        static {
            System.out.println("child静态代码块");
        }

        {
            System.out.println("child构造代码块");
        }

        public Child() {
            System.out.println("child构造方法");
        }

    }

    public static void main(String[] args) {
        new Child();
    }

}
