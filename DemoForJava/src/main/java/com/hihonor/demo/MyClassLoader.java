package com.hihonor.demo;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 实现自己的classloader
 * 应该是模板方法的设计模式
 * @author sunny
 */
public class MyClassLoader extends ClassLoader{

    private String classPath;

    public MyClassLoader(String classPath) {
        this.classPath = classPath;
    }

    /**
     * 核心方法，双亲委派机制由自带的loadclass实现，如果父类没有，即屌用该方法
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String packagePath = name.replaceAll("\\.", "/");
        try {
            FileInputStream fileInputStream = new FileInputStream(classPath + packagePath + ".class");
            byte[] content = new byte[fileInputStream.available()];
            fileInputStream.read(content);
            return defineClass(name, content, 0, content.length);
        } catch (IOException e) {
            throw new ClassNotFoundException();
        }
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)) {
            // First, check if the class has already been loaded
            Class<?> c = findLoadedClass(name);
            if (c == null) {
                if (name.startsWith("java1")) {
                    c = findClass(name);
                } else {
                    c = getParent().loadClass(name);
                }
            }
            if (resolve) {
                resolveClass(c);
            }
            return c;
        }
    }

    public static void main(String[] args) throws Throwable {
        MyClassLoader classLoader = new MyClassLoader("/Users/sunny/temp/");
        Class<?> loadClass = classLoader.loadClass("java1.lang1.String1");
        System.out.println(loadClass.getClassLoader());
        Object o = loadClass.newInstance();
        Method work = loadClass.getDeclaredMethod("work");
        work.invoke(o);
    }

}
