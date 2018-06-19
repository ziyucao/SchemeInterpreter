package com.ecnu.utils.Procedure;

import com.ecnu.utils.AbPair;
import com.ecnu.utils.Frame;
import com.ecnu.utils.Pair;
import com.ecnu.utils.nil;

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
            System.out.println("args type error!");
        }
        int count = 0;
        if (! scheme_listp(args)) {
            System.out.print("arguments are not in a list");
            return false;
        }
        int[] java_args = new int[maxcount];

        while (!args.getClass().isInstance(nil.getInstance()))
        {
            //将scheme的pair转换为java的List形式
            java_args[count] = args.getInt();
            count ++;
            args = (AbPair) args.getSecond();
        }

        try{
            return fnprocess(fn,java_args);
        }
        catch(Exception e)
        {
            System.out.print("TYPE ERROR!");
            return false;

        }

    }



}
