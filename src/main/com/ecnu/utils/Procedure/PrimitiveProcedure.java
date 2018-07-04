package com.ecnu.utils.Procedure;

import com.ecnu.basic.SchemeList;
import com.ecnu.utils.*;
import org.omg.CORBA.OBJ_ADAPTER;

import java.lang.invoke.SwitchPoint;
import java.util.ArrayList;
import java.util.function.Function;

import static com.ecnu.primitive.primitive.scheme_listp;
import static com.ecnu.process.FnProcess.fnprocess;

public class PrimitiveProcedure extends Procedure{

    String name;
    String fn;
    Boolean use_env;

    public PrimitiveProcedure(String fn, boolean use_env, String name ){
        this.name = name;
        this.fn = fn;
        this.use_env = use_env;
    }

    public Object apply(AbPair args, Frame env)
    {
        int maxcount = 999;
        try{
        maxcount = args.getLength();}
        catch (Exception e)
        {
            ErrorInfo.setError("args type error!");
        }
        int count = 0;
        if (! scheme_listp(args)) {
            System.out.print("arguments are not in a list");
            return false;
        }
        Object[] java_args = new Object[maxcount];

        while (!args.getClass().isInstance(nil.getInstance()))
        {
            //将scheme的pair转换为java的List形式
            java_args[count] = args.getVal();
            if(java_args[count]==null){
                ErrorInfo.setError("TYPE Error!");
                ErrorInfo.setPosition(SchemeList.findByToken(args.getFirst().toString()));
                return false;
            }
            count ++;
            args = (AbPair) args.getSecond();
        }

        try{
            return fnprocess(fn,java_args);
        }
        catch(Exception e)
        {
            ErrorInfo.setError("TYPE ERROR!");
            return false;
            //ErrorInfo.setPosition(SchemeList.findByToken());
        }

    }

    public String toString(){
        switch(name){
            case "div":
                return "/";
            case "add":
                return "+";
            case "mul":
                return "*";
            case "sub":
                return "-";
            case "boolean?":
                return "boolean?";
        }
        return "";
    }



}
