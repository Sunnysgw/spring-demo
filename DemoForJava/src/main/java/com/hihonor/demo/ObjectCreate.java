package com.hihonor.demo;

import java.sql.Time;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.Temporal;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class ObjectCreate {

    private static class User {
        Integer id;
        String name;

        public void setId(Integer id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    /**
     * -Xmx15m -Xms15m -XX:+PrintGC -XX:-DoEscapeAnalysis 表现会很糟糕
     * @param args
     */
    public static void main(String[] args) {
        Instant start = Instant.now();
        for (int i = 0; i < 100000000; i++) {
            alloc();
        }
        long dur = Duration.between(start, Instant.now()).toMillis();
        System.out.println(dur);
    }

    private static void alloc() {
        User user = new User();
        user.setId(1);
        user.setName("hello");
    }

}
