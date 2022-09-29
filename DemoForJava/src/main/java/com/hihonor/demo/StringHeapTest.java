package com.hihonor.demo;

import java.util.ArrayList;

public class StringHeapTest {

    public static void main(String[] args) throws InterruptedException {
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            strings.add(i + "");
            Thread.sleep(1);
        }
    }

}
