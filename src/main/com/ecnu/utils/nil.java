package com.ecnu.utils;

public class nil extends AbPair {
    //空列表
    //按照自己的理解改成了单例模式

    private static nil instance;
    private nil(){
        first = null;
        second = null;
    }
    public static nil getInstance(){
        if(instance!=null){
            return instance;
        }
        instance = new nil();
        return instance;
    }

    public String toString(){
        return "nil";
    }

    public String print(){
        return "";    }
}
