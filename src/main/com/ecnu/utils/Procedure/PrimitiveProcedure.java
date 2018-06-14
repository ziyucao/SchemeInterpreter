package com.ecnu.utils.Procedure;

import com.ecnu.utils.Frame;
import com.ecnu.utils.Pair;
import com.ecnu.utils.nil;

import java.util.ArrayList;
import java.util.function.Function;

public class PrimitiveProcedure extends Procedure{

    String name;
    Function fn;
    Boolean use_env;

    public PrimitiveProcedure(Function fn, boolean use_env= false, String name = 'primitive'){
        this.name = name;
        this.fn = fn;
        this.use_env = use_env;
    }

    public int apply(Pair args, Frame env)
    {
        if (! scheme_listp(args))
            System.out.print("arguments are not in a list");
        ArrayList java_args = new ArrayList();

        while (!args.getClass().isInstance(nil.getInstance()))
        {
            //将scheme的pair转换为java的List形式
            java_args.add(args.getFirst());
            args = (Pair) args.getSecond();
        }

        if (this.use_env)
            java_args.add(env);
        try{
            //TODO:solve error
            return this.fn(java_args);
        }
        catch(Exception e)
        {
            System.out.print("TYPE ERROR!");
        }

    }



}
