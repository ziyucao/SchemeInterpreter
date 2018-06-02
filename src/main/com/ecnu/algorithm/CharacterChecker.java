package com.ecnu.algorithm;

public class CharacterChecker
{

    public static boolean isNumber(char c, int base)
    {
        switch (base)
        {
            case 2: return isBinaryNumber(c);
            case 8: return isOctalNumber(c);
            case 10: return isDecimalNumber(c);
            case 16: return isHexNumber(c);
            default: return false;
        }
    }

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
