package com.chaos.gradle.plugin.jarjar.demo;

import okio.AsyncTimeout;
import org.jetbrains.annotations.NotNull;

public class Test {
    public static void main(String[] args) {
        AsyncTimeout timeout = new AsyncTimeout();
        System.out.println(timeout);
    }

    public static void test(@NotNull String arg) {

    }
}
