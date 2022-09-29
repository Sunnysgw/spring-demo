package com.hihonor.demo;

public class LoadClass {

    static {
        System.out.println("hello");
    }

    public static void main(String[] args) {
        LoadClass loadClass = new LoadClass();
        Class<? extends LoadClass> aClass = loadClass.getClass();

    }

}
