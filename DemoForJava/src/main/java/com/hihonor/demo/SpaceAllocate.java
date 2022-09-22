package com.hihonor.demo;

import java.net.URL;
import java.net.URLClassLoader;

public class SpaceAllocate {

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    public static void main(String[] args) {
        byte[] bytes = new byte[6 * 1024 * 1024];
        byte[] bytes1 = new byte[8 * 1024];
    }

}
