package com.restaurant.settings;

import java.io.File;

public class Settings {

    private static final String dataDirectory = "data";

    public static String filenameTest() {
        return dataDirectory + File.separator + "test.txt";
    }

    public static String filenameOrders() {
        return dataDirectory + File.separator + "orders.txt";
    }

    public static String filenameMenu() {
        return dataDirectory + File.separator + "menu.txt";
    }

    public static String filenameCookbook() {
        return dataDirectory + File.separator + "cookbook.txt";
    }

    public static String filenameNewCookbook() {
        return dataDirectory + File.separator + "new-cookbook.txt";
    }

    public static String filenameNewMenu() {
        return dataDirectory + File.separator + "new-menu.txt";
    }

    public static String delimiter() {
        return "\t";
    }
}

