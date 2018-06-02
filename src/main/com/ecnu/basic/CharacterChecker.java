package com.ecnu.basic;

public class CharacterChecker {

    public static boolean isBinaryNumber(char c)
    {
        return (c == '0' || c== '1');
    }

    public static boolean isOctalNumber(char c)
    {
        return (c >= '0' && c <= '7');
    }

    public static boolean isDecimalNumber(char c)
    {
        return (c >= '0' && c <= '9');
    }

    public static boolean isHexNumber(char c)
    {
        return  (c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F');
    }

    public static boolean isLetter(char c)
    {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    public static boolean isStringCharacter(char c)
    {
        return !(c == '\\' || c == '"');
    }
}
