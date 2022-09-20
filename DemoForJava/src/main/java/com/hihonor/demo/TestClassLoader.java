package com.hihonor.demo;

import com.sun.crypto.provider.DESKeyFactory;
import sun.misc.Launcher;

import java.net.URL;
import java.util.Arrays;

public class TestClassLoader {

    public static void main(String[] args) {
        System.out.println(String.class.getClassLoader());
        System.out.println(DESKeyFactory.class.getClassLoader().getParent());
        System.out.println(TestClassLoader.class.getClassLoader().getParent());

        System.out.println();
        System.out.println(ClassLoader.getSystemClassLoader());

        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        Arrays.stream(urLs).forEach(System.out::println);

        System.out.println(System.getProperty("java.ext.dirs"));

        System.out.println(System.getProperty("java.class.path"));
    }

}
