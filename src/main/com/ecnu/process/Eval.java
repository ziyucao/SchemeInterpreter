package com.ecnu.process;

import com.ecnu.basic.SchemeList;
import com.ecnu.utils.*;
import com.ecnu.utils.Procedure.PrimitiveProcedure;
import com.ecnu.utils.Procedure.Procedure;
import com.google.gson.JsonObject;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.ArrayList;
import java.util.Scanner;

import static com.ecnu.primitive.primitive.*;
import static com.ecnu.process.SchemeReader.scheme_read;
import static com.ecnu.utils.SpecialForms.SPECIAL_FORMS;

public class Eval {

    public static ArrayList special_forms = new ArrayList();

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
        if (!scheme_listp(expr)) {
            ErrorInfo.setError("表达式格式错误！");
            return null;
        }//不是直接返回报错

        //肯定不是Token了
        AbPair first = (AbPair) expr.getFirst();
        AbPair rest = (AbPair)expr.getSecond();
        /*和之前一样，如果first是一个在符号表里有的symbol那么根据符号的含义不同，
          调用各自符号的对应函数就可以了
        */

        if (scheme_identifierp(first) && special_forms.contains(first.toString()))
            return SPECIAL_FORMS(first.toString(), rest, env);

        //不然就是最后一种情况，expr的first也是一个复合表达式，那么就需要先对first evaluate
        //else
        {
            Procedure operator = (Procedure) scheme_eval(first, env);
            if(check_procedure(operator))
                //直接返回处理结果，比如(* 1 2)，那么*相当于operator,其对应的procedure对rest，也就是(1 2)这个pair进行处理
                return operator.eval_call((AbPair)rest,env);
        }

        return "";


    }


    public static void setSpecial_forms(){
        special_forms.add("and");
        special_forms.add("begin");
        special_forms.add("cond");
        special_forms.add("define");
        special_forms.add("if");
        special_forms.add("lambda");
        special_forms.add("let");
        special_forms.add("or");
        special_forms.add("quote");
        special_forms.add("define-macro");
    }

    //判断expr是不是一个可以直接得值的simple的表达式
    public static boolean self_evaluating(AbPair expr)
    {
        return scheme_atomp(expr) || scheme_stringp(expr) || expr == null;
    }

    //在给定框架中将这个scheme程序应用在args上
    public static Object scheme_apply(Procedure procedure, AbPair args, Frame env)
    {
        check_procedure(procedure);
        return procedure.apply(args,env);
    }

    //在给定框架中应用所有表达式，并且返回最后一个的值(do_begin_form中用到)
    public static Object eval_all(AbPair expressions, Frame env)
    {
        if(!(expressions instanceof nil))
        {
            AbPair rest = (AbPair) expressions.getSecond();
            if (rest instanceof nil)
                return scheme_eval((AbPair) expressions.getFirst(), env);
            else
            {
                //算第一个表达式，再递归rest
                scheme_eval((AbPair)expressions.getFirst(), env);
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

    public static void check_form(AbPair expr, int min, int max){
        int length;
        if (! scheme_listp(expr))
        {
            System.out.println("表达式格式错误！");
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
        {System.out.println("表达式长度错误！");
            throw new RuntimeException();}

    }

    public static void read_eval_print_loop(SchemeList src, Frame env, JsonObject jsonObject)
    {
        AbPair expression = new AbPair();
        try {
            expression = scheme_read(src);
        }
        catch(Exception e)
        {
            ErrorInfo.setError("输入格式错误！");
        }
      //  System.out.println(expression.toString());
        Object result = scheme_eval(expression, env);
        if (result != null)
           jsonObject.addProperty("Result",result.toString());
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
        env.define("boolean?",
                new PrimitiveProcedure("scheme_booleanp", true, "sub"));
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
