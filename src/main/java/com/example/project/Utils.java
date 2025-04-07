package com.example.project;

// Useless class - was thinking about using it

public class Utils {
    public static boolean in(String[] arr, String target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(target)) {
                return true;
            }
        }
        return false;
    }
}
