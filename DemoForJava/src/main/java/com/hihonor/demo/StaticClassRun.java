package com.hihonor.demo;

public class StaticClassRun {

    private static class Child {

        public static String name = "tom";

        static {
            System.out.println("hello");
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        Class<?> aClass = systemClassLoader.loadClass("com.hihonor.demo.LoadClass");
        Object o = aClass.newInstance();
        System.out.println("test");
        Class.forName("com.hihonor.demo.LoadClass");
    }

}
