package com.example.recycledview.Util;

public class StringUtil{
    public static String formattemp(double temp){
        String s = String.valueOf(temp);
        return s.concat(" °c");
    }
    public static String formathum(double hum){
        String s = String.valueOf(hum);
        return s.concat(" %");
    }
    public static String formatwind(double wind){
        String s = String.valueOf(wind);
        return s.concat(" m/s");
    }
    public static String formatpress(double press){
        String s = String.valueOf(press);
        return s.concat(" kpa");
    }

}