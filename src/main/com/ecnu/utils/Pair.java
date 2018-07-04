package com.ecnu.utils;

import com.ecnu.Exception.TypeError;
import com.ecnu.basic.SchemeList;
import com.ecnu.basic.SchemeNumber;
import com.ecnu.process.SchemeReader;
import com.sun.xml.internal.ws.api.pipe.helper.PipeAdapter;

import java.io.PipedReader;
import java.util.ArrayList;
import java.util.List;

import static com.ecnu.process.Eval.scheme_eval;

public class Pair extends AbPair{
    private AbPair first;
    private AbPair second;

    public Pair(AbPair first,AbPair second){
        this.first = first;
        this.second = second;
    }

    public Pair(){
        first = null;
        second = null;
    }

    public void setSecond(AbPair second){
        this.second = second;
    }

    public int getLength() throws TypeError{
        int n = 1;
        AbPair second = this.second;
        while(second instanceof Pair){
            n++;
            second = ((Pair) second).second;
        }
        if(!(second instanceof nil)){
            throw new TypeError("length attempted on improper list");
        }
        return n;
    }

    public String toString() {
        String result = "Pair(" + this.first.toString() + ",";
        AbPair second = this.second;
        int i = 1;
        while(second instanceof Pair){
            i++;
            result += "Pair("+((Pair) second).first.toString()+",";
            second = ((Pair) second).second;
        }
        if(!(second instanceof nil)){
            result+=second.toString();
        }
        else{
            result += nil.getInstance().toString();
        }
        while(i>0){
            i--;
            result+=")";
        }
        return result;
    }



    public String print() {
        String result = "(" + this.first.print();
        AbPair second = this.second;
        int i = 1;
        while(second instanceof Pair){
            i++;
            result += " " + ((Pair) second).first.print();
            second = ((Pair) second).second;
        }
        if(!(second instanceof nil)){
            result+= "."+second.print();
        }
        return result + ")";
    }



    public AbPair getFirst(){
        return first;
    }
    public AbPair getSecond() {
        return second;
    }
}
