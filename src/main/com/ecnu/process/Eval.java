package com.ecnu.process;

import com.ecnu.basic.SchemeList;
import com.ecnu.utils.AbPair;
import com.ecnu.utils.Frame;
import com.ecnu.utils.Pair;
import com.ecnu.utils.Procedure.PrimitiveProcedure;
import com.ecnu.utils.Procedure.Procedure;
import com.ecnu.utils.nil;

import java.util.Scanner;

import static com.ecnu.primitive.primitive.*;
import static com.ecnu.process.SchemeReader.scheme_read;

public class Eval {

    //TODO:同一scheme_eval的返回类型
    public static Object scheme_eval(AbPair expr, Frame env)
    {
        //分四种情况

        //如果expr是已经存在的标识符那么在环境里查找这个标识输出它binding的值即可
        //System.out.println(expr.toString());
            if (scheme_symbolp(expr))
            return env.lookup(expr);
            //不然如果是可以直接处理的布尔类型，数字或者空的话，直接返回
        else if (self_evaluating(expr))
            return expr;

        //剩下的情况这个表达式一定要是list，因此先进行判断
        if (!scheme_listp(expr))
            System.out.print( "SchemeError!");//不是直接返回报错

        //肯定不是Token了
        AbPair first = (AbPair) expr.getFirst();
        AbPair rest = (AbPair)expr.getSecond();
        /*和之前一样，如果first是一个在符号表里有的symbol那么根据符号的含义不同，
          调用各自符号的对应函数就可以了
        */

        //TODO:在java里用python类似的方法找到符号的对应函数
        /*if (scheme_symbolp(first) && first in SPECIAL_FORMS)
        return SPECIAL_FORMS[first](rest, env);*/

        //不然就是最后一种情况，expr的first也是一个复合表达式，那么就需要先对first evaluate
        //else
        {
            Procedure operator = (Procedure) scheme_eval(first, env);
            if(check_procedure(operator))
                //直接返回处理结果，比如(* 1 2)，那么*相当于operator,其对应的procedure对rest，也就是(1 2)这个pair进行处理
                return operator.eval_call((Pair)rest,env);
        }

        return "";


    }

    //判断expr是不是一个可以直接得值的simple的表达式
    public static boolean self_evaluating(AbPair expr)
    {
        return scheme_atomp(expr) || scheme_stringp(expr) || expr == null;
    }

    //在给定框架中将这个scheme程序应用在args上
    public static Object scheme_apply(PrimitiveProcedure procedure, AbPair args, Frame env)
    {
        check_procedure(procedure);
        return procedure.apply(args,env);
    }

    //在给定框架中应用所有表达式，并且返回最后一个的值
    public static Object eval_all(Pair expressions, Frame env)
    {
        if((AbPair)expressions != nil.getInstance())
        {
            Pair rest = (Pair)expressions.getSecond();
            if ((AbPair)rest == nil.getInstance())
                return scheme_eval((Pair)expressions.getFirst(), env);
            else
            {
                //算第一个表达式，再递归rest
                scheme_eval((Pair)expressions.getFirst(), env);
                return eval_all(rest,env);
            }

        }
        return null;

    }

    public static boolean check_procedure(Procedure p)
    {
        return scheme_procedurep(p);
    }

    public static <T> boolean scheme_procedurep(T x) {return x instanceof Procedure;}

    public static void check_form(Pair expr, int min, int max){
        int length;
        if (! scheme_listp(expr))
        {
            throw new RuntimeException();
        }
        try
        {
            length = expr.getLength();
        }
        catch(Exception e)
        {
            throw new RuntimeException();
        }
        if (length < min || length > max)
            throw new RuntimeException();

    }

    public static void read_eval_print_loop(SchemeList src, Frame env)
    {
        AbPair expression = new AbPair();
        try {
            expression = scheme_read(src);
        }
        catch(Exception e)
        {
            System.out.println("input error!");
        }
        Object result = scheme_eval((Pair) expression, env);

        System.out.println(result);
    }


    public static Frame create_global_frame()
    {
        Frame env = new Frame(null);
        env.define("eval",
                new PrimitiveProcedure("scheme_eval", true, "eval"));
        env.define("apply",
                new PrimitiveProcedure("scheme_apply", true, "apply"));
        env.define("load",
                new PrimitiveProcedure("scheme_load", true, "load"));
        env.define("procedure?",
                new PrimitiveProcedure("scheme_procedurep", false, "procedure?"));
        env.define("map",
                new PrimitiveProcedure("scheme_map", true, "map"));
        env.define("filter",
                new PrimitiveProcedure("scheme_filter", true, "filter"));
        env.define("reduce",
                new PrimitiveProcedure("scheme_reduce", true, "reduce"));

        env.define("*",
                new PrimitiveProcedure("scheme_mul", true, "mul"));
        env.define("/",
                new PrimitiveProcedure("scheme_div", true, "div"));
        env.define("+",
                new PrimitiveProcedure("scheme_add", true, "add"));
        env.define("-",
                new PrimitiveProcedure("scheme_sub", true, "sub"));

        env.define("undefined", null);
        //add_primitives(env, PRIMITIVES);
        return env;
    }

    public void main()
    {
        Scanner sc = new Scanner(System.in);
        String content = sc.next();
        //read_eval_print_loop(content, create_global_frame());

    }

}
