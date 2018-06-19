package com.ecnu.utils;

public class CompareDelimiter {
    //  set("()[]'`")
    //{'.', ',', ',@'}
    private static String [] delimiters = {".",",",",@","(",")","[","]","'","\""};

    public static boolean isDelimiter(String val){
        boolean result = false;
        for (String str:delimiters){
            result |= val.equals(str);
        }
        return result;
    }

}
