package com.ecnu.utils;

import com.ecnu.utils.Procedure.Procedure;

import java.util.*;

//将scheme中的symbols和values绑定的框架
public class Frame {

    Map bindings;//使用map来存储symbol和value的对应关系
    Frame parent = null;

    public Frame(Frame parent)
    {
        this.bindings = new HashMap();
        this.parent = parent;
    }

    public void define(String symbol, Procedure value )
    {
        //将symbol和value绑定在一起
        this.bindings.put(symbol,value);
    }

    public Procedure lookup(AbPair symbol)
    {
        //当前框架里找得到symbol的话直接返回对应的值
        if (this.bindings.containsKey(symbol.toString())) {
            return (Procedure) this.bindings.get(symbol.toString());
        }
        //不然父亲不为空的话去父亲里找
        else if (this.parent!= null)
            return this.parent.lookup(symbol);
        //没父亲自己又没有这个symbol的话只能报错了
        System.out.println("unknown identifier");
        return new Procedure();
    }

    //批量绑定symbol给子框架
    public Frame make_child_frame(Pair formals, Pair vals)
    {
        Frame child = new Frame(this);
        try {
            if (formals.getLength() != vals.getLength()) {
                System.out.println("too many or too few vals are given");
                return new Frame(null);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        while (!formals.getClass().isInstance(nil.getInstance()))//判断formals是不是nil,这里nil使用了单例模式
        {
            child.bindings.put(formals.getFirst(),vals.getFirst());//将formals中的key和value放进child的binding中
            formals = (Pair) formals.getSecond();
            vals = (Pair) vals.getSecond();
        }
        return child;

    }



}
