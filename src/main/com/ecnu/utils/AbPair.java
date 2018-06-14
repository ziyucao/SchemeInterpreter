package com.ecnu.utils;

import com.ecnu.Exception.TypeError;

public class AbPair<T1,T2> {
    protected T1 first;
    protected T2 second;

    public AbPair(T1 first,T2 second){
        this.first = first;
        this.second = second;
    }

    public AbPair(){
        first = null;
        second = null;
    }

    public void setFirst(T1 first){
        this.first = first;
    }

    public void setSecond(T2 second){
        this.second = second;
    }

    public int getLength() throws TypeError {
        return 0;
    }

    public String print(){
        return toString();
    }
    public T1 getFirst(){
        return first;
    }
    public T2 getSecond() {
        return second;
    }
}