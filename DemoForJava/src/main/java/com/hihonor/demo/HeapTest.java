package com.hihonor.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HeapTest {

    private byte[] data = new byte[1024];

    public static void main(String[] args) throws InterruptedException {
        List<HeapTest> list = new ArrayList<>();
        int i = 0;
        for(;;) {
            System.out.println(i++);
            list.add(new HeapTest());
            TimeUnit.MICROSECONDS.sleep(10000);
        }
    }

}
