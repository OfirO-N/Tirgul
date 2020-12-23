package com.catalog.oon.utils;

/**
 * Created by kobis on 27 Nov, 2020
 */
public class TestsUtils {
    private static int COUNTER=0;
    public static void printTestInfo(String testName) {
        System.out.println(String.format("Developer Test : %d | Test: %s",++COUNTER,testName));
    }

}
