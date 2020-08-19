package com.chaos.gradle.plugin.jarjar.demo;

import com.chaos.annotations.NotNull;
import com.chaos.okio.AsyncTimeout;

public class Test {
    public static void main(String[] args) {
        AsyncTimeout timeout = new AsyncTimeout();
        System.out.println(timeout);
    }

    public static void test(@NotNull String arg) {

    }
}
