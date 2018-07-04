package com.ecnu.utils;

import com.ecnu.Exception.SchemeException;
import com.ecnu.Exception.TypeError;
import com.ecnu.basic.SchemeBoolean;
import com.ecnu.basic.SchemeIdentifier;
import com.ecnu.basic.SchemeList;
import com.ecnu.basic.SchemeNumber;
import com.ecnu.primitive.primitive;
import com.ecnu.process.Eval;
import com.ecnu.utils.Procedure.LambdaProcedure;
import com.ecnu.utils.Procedure.SymbolProcedure;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static com.ecnu.primitive.primitive.*;
import static com.ecnu.process.Eval.check_form;
import static com.ecnu.process.Eval.eval_all;
import static com.ecnu.process.Eval.read_eval_print_loop;

public class SpecialForms {

    public static ArrayList special_form = new ArrayList();



    public static Object SPECIAL_FORMS(String first,  AbPair expressions, Frame env)
    {
        switch(first) {
            case "and":
                return do_and_form(expressions,env);
            case "begin":
                return do_begin_form(expressions,env);
            case "cond":
                break;
            case "define":
                try {
                    return do_define_form(expressions, env);
                }
                catch (SchemeException se){
                    ErrorInfo.setError(se.getMessage());
                    break;
                }
            case "if":
                try {
                    return do_if_form((Pair) expressions,env);
                } catch (SchemeException e) {
                    ErrorInfo.setError(e.getMessage());
                }
            case "lambda":
                try {
                    return do_lambda_form((Pair) expressions,env);
                } catch (SchemeException e) {
                    ErrorInfo.setError(e.getMessage());
                }
            case "let":
                return do_let_form((Pair)expressions,env);
            case "or":
                return do_or_form(expressions,env);
            case "quote":
            case "'":
                return do_quote_form(expressions,env);
            case  "define-macro":
                break;
        }
        return false;
    }

    private static Object do_and_form(AbPair expressions, Frame env){
        Object last = new Object();
        if (expressions instanceof nil)
            return true;
        while (! (expressions instanceof nil))
        {
            last = Eval.scheme_eval( (AbPair) expressions.getFirst(), env);
            if (! scheme_truep(last))
                return false;
            expressions = (AbPair)expressions.getSecond();
        }
        return last;
    }

    private static Object do_begin_form(AbPair expressions, Frame env){
        check_form(expressions,1,9999);
        return Eval.eval_all(expressions, env);
    }

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
private static Frame make_let_frame(AbPair bindings, Frame env) throws Exception {
    if (!scheme_listp(bindings)) {
        throw new SchemeException("bad bindings list in let form");
    }
    AbPair symbol = nil.getInstance();
    AbPair expression = nil.getInstance();
    while (!bindings.equals(nil.getInstance())) {
        Pair current = (Pair) bindings.getFirst();
        check_form((Pair) (current), 2, 2);
        // #print ("result of check_formals:",check_formals(current.first))
        // #print ("@bindings:",bindings)
        // #print("@current:",current)
        symbol = new Pair((AbPair) current.getFirst(), symbol);
        expression = new Pair((AbPair) Eval.scheme_eval((AbPair) current.getSecond().getFirst(), env), expression);

        // #print("@symbol:",symbol)
        // #print("@expression",expression)
        bindings = (AbPair) bindings.getSecond();
    }
    Frame let_env = env.make_child_frame((Pair) symbol, (Pair) expression);
    // #check whether the symbol is a list containing a symbol.
    try {
        check_formals((Pair) symbol);
    } catch (SchemeException se) {
        ErrorInfo.setError(se.getMessage());
    }
    //  #print("let_env:",let_env)

    return let_env;
}

    public static void check_formals(AbPair formals) throws SchemeException {
        Set<Token> symbols = new HashSet<>();
        while (formals instanceof Pair) {
            if (!scheme_symbolp(formals.getFirst())) {
                throw new SchemeException("non-symbol: " + ((Token) formals.getFirst()).getToken().toString());
            }
            if (symbols.contains(formals.getFirst())) {
                throw new SchemeException("duplicate symbol: " + ((Token) formals.getFirst()).getToken().toString());
            }
            symbols.add((Token) formals.getFirst());
            formals = (AbPair) formals.getSecond();
        }
    }

    private static Object do_let_form(AbPair expressions, Frame env) {
        check_form(expressions, 2, 9999);
        Frame let_env = null;
        try {
            let_env = make_let_frame((AbPair) expressions.getFirst(), env);
        } catch (Exception se) {
            ErrorInfo.setError(se.getMessage());
        }
        return Eval.eval_all((Pair) (expressions.getSecond()), let_env);
    }

    private static Object do_or_form(AbPair expressions,Frame env) {
        while(!expressions.equals(nil.getInstance())) {
            Object last = Eval.scheme_eval((AbPair) expressions.getFirst(), env);
            if (scheme_truep(last)) {
                return last;
            }
            expressions = (AbPair) expressions.getSecond();
        }
        return new SchemeBoolean(new Boolean(false));
    }

    private static Object do_quote_form(AbPair expressions,Frame env) {
        check_form(expressions, 1, 1);
        return expressions.getFirst();
    }

    private static Object do_define_form(AbPair expressions, Frame env) throws SchemeException {
        Eval.check_form(expressions,2,9999);
        AbPair target = (AbPair) expressions.getFirst();
        if(scheme_symbolp(target)){
            Eval.check_form(expressions,2,2);
            /*
            如(define a 2)，
            (String) scheme_eval((AbPair) expressions.getSecond().getFirst(),env) 得到 a
            */
            Object object = Eval.scheme_eval((AbPair) ((AbPair) expressions.getSecond()).getFirst(),env);
            if(object instanceof Integer){
                env.define(target.toString(),new SymbolProcedure(new Token(new SchemeNumber((Integer)object+""))));
            }
            else if(object instanceof Token){
                env.define(target.toString(),new SymbolProcedure((Token) object));
            }
            else if(object instanceof Boolean){
                env.define(target.toString(),new SymbolProcedure(new Token(new SchemeBoolean((Boolean)object))));
            }
            else{
                env.define(target.toString(),(LambdaProcedure)object);
            }
            return target;
        }else if((target instanceof Pair)&&scheme_symbolp(target.getFirst())){
            env.define(target.getFirst().toString(), new LambdaProcedure((AbPair) target.getSecond(),(AbPair) expressions.getSecond(),env));
            return target.getFirst();
        }else {
            AbPair bad_target = new AbPair();
            if ((target instanceof Pair)) {
                bad_target = (AbPair) target.getFirst();
            } else {
                bad_target = target;
            }

            throw new SchemeException(String.format("non-symbol: {0}\n错误字符：%s\n错误下标:%s", bad_target, SchemeList.findByToken(bad_target.toString())));
        }

    }

    private static Object do_if_form(Pair expressions, Frame env) throws SchemeException {
        Eval.check_form(expressions,2,3);
        try {
            if (scheme_truep(Eval.scheme_eval(expressions.getFirst(), env))) {
                return Eval.scheme_eval((AbPair) expressions.getSecond().getFirst(), env);
            } else if (expressions.getLength() == 3 && (expressions.getSecond().getSecond() instanceof Pair)) {
                Pair pair = (Pair) expressions.getSecond().getSecond();
                return Eval.scheme_eval(pair.getFirst(), env);
            }
        }
        catch (TypeError te){
            ErrorInfo.setError(te.getMessage());
        }
        return null;
    }

    //    特殊形式lambda用于定义过程。lambda需要至少一个的参数，第一个参数是由定义的过程所需的参数组成的表。
//    用lambda定义函数是一种规范的方法
    private static Object do_lambda_form(Pair expressions, Frame env) throws SchemeException {
        Eval.check_form(expressions,2,9999);
        AbPair formals = expressions.getFirst();
        check_forms((Pair) formals);
        return (new LambdaProcedure(expressions.getFirst(),expressions.getSecond(),env));
    }

    private static Object do_cond_form(AbPair expressions, Frame env) throws SchemeException {
        Pair clause = new Pair();
        boolean test=false;
        while((AbPair)expressions != nil.getInstance())
        {
            clause = (Pair)expressions.getFirst();
            Eval.check_form(clause, 1, 9999);
            //TODO:先判断是不是string，直接下面这样可能会报错？
            if (((clause.getFirst()) instanceof Token))
            {
                Token tk= (Token) clause.getFirst();
                if(tk.toString().equals("else")){
                    test=true;
                    if(expressions.getSecond() != nil.getInstance())
                    {
//                        System.out.println("else must be last");
                        throw  new SchemeException("else must be last");
                    }
                }
            }
            else if (scheme_truep(test)){
                if(clause.getSecond() !=nil.getInstance()){
                    return Eval.eval_all(expressions,env);
                }
                return test;
            }
            expressions= (Pair) expressions.getSecond();
        }
        return null;
    }

    private static void check_and_add(AbPair symbol, Set symbols) throws SchemeException {
        if(!scheme_symbolp(symbol)){
            throw new SchemeException(String.format("non-symbol: {0}\n错误字符：%s\n错误下标:%s", symbol, SchemeList.findByToken(symbol.toString())));
//            throw new SchemeException(String.format("non-symbol: {0}%s",symbol));
        }

        if(symbols.contains(symbol)){
            throw new SchemeException(String.format("non-symbol: {0}\n错误字符：%s\n错误下标:%s", symbol, SchemeList.findByToken(symbol.toString())));
//            throw new SchemeException(String.format("duplicate symbol: {0}%s",symbol));
        }else {
            symbols.add(symbol);
        }

    }

    public static void check_forms(Pair formals) throws SchemeException
    {
    /*    检查格式是否是一个有效的参数列表，即每个符号都是不同的符号的方案列表。
        如果表单的列表不是一个格式良好的符号列表，或者重复出现任何符号，那么就创建一个SchemeError。*/
        Set symbols=new HashSet();
        while (formals instanceof Pair){
            //检查是否出现重复符号
            check_and_add(formals.getFirst(),symbols);
//        直到遍历到formals的最后一个是nil
            if(formals.getSecond()==nil.getInstance()){
                break;
            }else {
                formals=(Pair)formals.getSecond();
            }
        }
    }

}
