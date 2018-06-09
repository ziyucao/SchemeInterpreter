package com.ecnu.process;

import com.ecnu.utils.AbPair;
import com.ecnu.utils.Frame;
import com.ecnu.utils.Pair;
import com.ecnu.utils.Procedure.PrimitiveProcedure;
import com.ecnu.utils.Procedure.Procedure;

public class Eval {

    //TODO:同一scheme_eval的返回类型
    public static Pair scheme_eval(Pair expr, Frame env)
    {
        //分四种情况

        //如果expr是已经存在的标识符那么在环境里查找这个标识输出它binding的值即可
        if (scheme_symbolp(expr))
            return env.lookup(expr);
            //不然如果是可以直接处理的布尔类型，数字或者空的话，直接返回
        else if (self_evaluating(expr))
            return expr;

        //剩下的情况这个表达式一定要是list，因此先进行判断
        if (!scheme_listp(expr))
            System.out.print( "SchemeError!");//不是直接返回报错

        AbPair first = expr.getFirst();
        AbPair rest = expr.getSecond();
        /*和之前一样，如果first是一个在符号表里有的symbol那么根据符号的含义不同，
          调用各自符号的对应函数就可以了
        */

        //TODO:在java里用python类似的方法找到符号的对应函数
        /*if (scheme_symbolp(first) && first in SPECIAL_FORMS)
        return SPECIAL_FORMS[first](rest, env);*/

        //不然就是最后一种情况，expr的first也是一个复合表达式，那么就需要先对first evaluate
        //else
        {
            Procedure operator = scheme_eval((Pair)first, env);
            if(check_procedure(operator))
                //直接返回处理结果，比如(* 1 2)，那么*相当于operator,其对应的procedure对rest，也就是(1 2)这个pair进行处理
                return operator.eval_call((Pair)rest,env);
        }




    }

    //判断expr是不是一个可以直接得值的simple的表达式
    public static boolean self_evaluating(Pair expr)
    {
        return scheme_atomp(expr) || scheme_stringp(expr) || expr == null;
    }

    //在给定框架中将这个scheme程序应用在args上
    public static int scheme_apply(PrimitiveProcedure procedure, Pair args, Frame env)
    {
        check_procedure(procedure);
        return procedure.apply(args,env);
    }

    /*//在给定框架中应用所有表达式，并且返回最后一个的值
    public static eval_all(Pair expressions, Frame env)
    {
        if(expressions != nil)
        {
            Pair rest = expressions.second;
            if (rest == nil)
                return scheme_eval(expressions.first, env);
            else
            {
                //算第一个表达式，再递归rest
                scheme_eval(expressions.first, env);
                return eval_all(rest,env);
            }

        }
        return None;

    }*/

    public static boolean check_procedure(Procedure p)
    {
        return scheme_procedurep(p);
    }


}
