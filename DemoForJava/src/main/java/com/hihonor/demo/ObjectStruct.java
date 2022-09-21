package com.hihonor.demo;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * @description: 使用joi包查看对象的内部结构
 * @author: sunnysgw
 * @since: 1.0
 **/
public class ObjectStruct {

    private int a = 1;

    public static void main(String[] args) throws InterruptedException {
        //TimeUnit.SECONDS.sleep(5);
        ClassLayout classLayout = ClassLayout.parseInstance(new ObjectStruct());
        System.out.println(classLayout.toPrintable());

        ClassLayout classLayout1 = ClassLayout.parseInstance(new int[10]);
        System.out.println(classLayout1.toPrintable());
    }

}
