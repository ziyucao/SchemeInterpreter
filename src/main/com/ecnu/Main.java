package com.ecnu;

public class Main
{
    public static void main(String[] args)
    {
        String a = "a\nb\rc d";
        String[] token = a.split("\n|\r| ");
        System.out.println(token[2]);
        // System.out.println("Hello World!");
    }
}
