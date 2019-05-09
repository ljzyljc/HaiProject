package com.hai.jackie.haiproject.annotation;

public class TestSinglton {
    private static final TestSinglton ourInstance = new TestSinglton();

    public static TestSinglton getInstance() {
        return ourInstance;
    }

    private TestSinglton() {
    }
}
