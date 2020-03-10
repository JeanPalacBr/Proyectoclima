package com.example.recycledview.Util;

public class StringUtils {
    public static String formatAge(int edad){
        String s = String.valueOf(edad);
        return s.concat(" años");
    }
}