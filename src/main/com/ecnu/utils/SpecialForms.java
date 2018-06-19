package com.ecnu.utils;

import com.ecnu.process.Eval;

import java.util.Objects;

public class SpecialForms {
//
//    public static Object SPECIAL_FORMS(String first,  Pair expressions, Frame env)
//    {
//        switch(first) {
//            case "and":
//                return do_and_form(expressions,env);
//            case "begin":
//                break;
//            case "cond":
//                break;
//            case "define":
//                break;
//            case "if":
//                break;
//            case "lambda":
//                break;
//            case "let":
//                break;
//            case "or":
//                break;
//            case "quote":
//                break;
//            case  "define-macro":
//                break;
//        }
//    }
//
//    private static Object do_and_form(Pair expressions, Frame env){
//        Object last = new Object();
//        if ((AbPair)expressions == nil.getInstance())
//            return true;
//        while ((AbPair)expressions != nil.getInstance())
//        {
//            last = Eval.scheme_eval( (Pair)expressions.getFirst(), env);
//            if (scheme_truep(last))
//                return false;
//            expressions = (Pair)expressions.getSecond();
//        }
//        return last;
//    }
//
//    private static Object do_begin_form(Pair expressions, Frame env){
//        Eval.check_form(expressions,1,9999);
//        return Eval.eval_all(expressions, env);
//    }
//
//    private static Object do_cond_form(Pair expressions, Frame env){
//        Pair clause = new Pair();
//        while((AbPair)expressions != nil.getInstance())
//        {
//            clause = (Pair)expressions.getFirst();
//            Eval.check_form(clause, 1, 9999);
//            //TODO:先判断是不是string，直接下面这样可能会报错？
//            if (((Object)clause.getFirst()).equals("else"))
//            {
//                if(expressions.getSecond() == nil.getInstance())
//                {
//                    throw  new RuntimeException();
//                }
//            }
//            else
//                //if (scheme_truep(test))
//        }
//    }


}
