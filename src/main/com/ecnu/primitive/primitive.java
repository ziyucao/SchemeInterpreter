package com.ecnu.primitive;

import com.ecnu.Exception.TypeError;
import com.ecnu.basic.SchemeBoolean;
import com.ecnu.basic.SchemeNumber;
import com.ecnu.basic.SchemeString;
import com.ecnu.utils.AbPair;
import com.ecnu.utils.Pair;
import com.ecnu.utils.Token;
import com.ecnu.utils.nil;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Map;

public class primitive {
    String primitive_Operations="";
    String str1="";
    public primitive(String primitive_Operations,String str1){
        Map<String,FunctionalInterface> m=null;
        this.primitive_Operations=primitive_Operations;
        this.str1=str1;
        switch (primitive_Operations)
        {
            case "boolean?":

        }


    }

    public static <T> boolean scheme_booleanp(T x){
        if (x instanceof Token)
            return ((Token) x).getToken() instanceof SchemeBoolean ;
        return false;

    }

    public static <T> boolean scheme_truep(T x){
        if(x instanceof Token)
            return ((Token) x).getToken().equals(true);
        return false;
    }
    public static <T> boolean scheme_falsep(T x){
        return x.equals(false);
    }

    public static <T>boolean scheme_not(T x){
        return !scheme_truep(x);
    }

    public static <T> boolean scheme_equal(T pair1, T pair2){
        if ((pair1 instanceof Pair)&&(pair2 instanceof Pair)){
            return scheme_equal(((Pair) pair1).getFirst(),((Pair) pair2).getFirst())&&scheme_equal(((Pair) pair1).getSecond(),((Pair) pair2).getSecond());
        }else
            return pair1.equals(pair2);
    }



//    public static boolean scheme_eqp(){
//
//    }

    public static <T> boolean scheme_pairp(T x){
        return x instanceof Pair;
    }

//    相当于main函数入口
//    public static boolean scheme_promisep(){
//
//    }

//    public static boolean scheme_force(){
//
//    }

//    public static boolean scheme_cdr_stream(){
//
//    }

    public static <T> boolean scheme_nullp(T pair){
        return pair instanceof nil;
    }

    //判断x是否是一个well-formed list，即都在pair里面的结构
    public static <T> boolean scheme_listp(T x){
        while (!(x instanceof nil)){
            if(!(x instanceof Pair))
                return false;
            x= (T) ((Pair) x).getSecond();
        }
        return true;
    }

    public static int scheme_length(Pair x) throws TypeError {
        return x.getLength();
    }

    //点对，是两个任意有序值的组合。第一个元素称为car，第二个元素称为cdr
    public static Pair scheme_cons(Pair x, Pair y){
        return new Pair(x,y);
    }


    public static AbPair scheme_car(Pair x){
        return x.getFirst();
    }

    public static AbPair scheme_cdr(Pair y){
        return y.getSecond();
    }

//    感觉是组成一个pair
//    public static Pair[] scheme_list(Pair[] s){
//        String
//        return s;
//    }

//    public static boolean scheme_append(String[] s){
//
//    }

//    判断是否是string,强类型语言不用吧。。。
    public static <T> boolean scheme_stringp(T s){
        if (s instanceof Token && s != null && (((Token) s).getToken() instanceof SchemeString))
        {String y=(String) s;
            if(y.substring(0,1)=="\"") return true;}
        return false;


    }


    public static <T> boolean scheme_symbolp(T x){
        if (x instanceof Token)
            return ((Token) x).getToken() instanceof SchemeString || x.toString().equals("*") || x.toString().equals( "/") || x.toString().equals("+") || x.toString().equals("-");

        return false;

    }

    public static <T> boolean scheme_numberp(T x){
//        return (x instanceof Integer)||(x instanceof Float)&&(!(x instanceof Boolean));
        if (x instanceof Token)
            return ((Token) x).getToken() instanceof SchemeNumber ;
        return false;
    }

    public static <T> boolean scheme_integerp(T x){
        //if (x instanceof Token)
            //return ((Token) x).getToken() instanceof Integer ;
        return false;

    }


    public static int scheme_add(int[] a){
        int result=0;
        for (int i=0;i<a.length;i++){
            result+=a[i];
        }
        return result;
    }

    //注意a是int型的，所以不用类型检测
    public static int scheme_sub(int[] a){

        if(a.length==0){
            return 0;
        }
        int result=a[0];
        for(int i=1;i<a.length;i++){

            result-=a[i];
        }
        return result;
    }
    //*
    public static int scheme_mul(int[] a){
        int result=1;
        for (int i=0;i<a.length;i++){

            result*=a[i];
        }
        return result;

    }

    public static int scheme_div(int[] a){
        if(a.length==0){
            return 0;
        }
        int result=a[0];
        for (int i=1;i<a.length;i++){

            try {
                result /=a[i];
            }catch (ArithmeticException e ){
                System.out.println("程序出现异常，除数不能为0。");
            }

        }
        return result;
    }

    public static double scheme_expt(int val0,int val1){
        return Math.pow(val0,val1);

    }

    public static double scheme_abs(double a){
        return Math.abs(a);
    }

    public static int scheme_quo(int val1,int val2){
        int result=0;
        try {
            result=(val1/val2);
        }catch (ArithmeticException e){
            System.out.println("程序出现异常，求商时除数不能为0。");
        }
        return result;

    }

    public static int scheme_modulo(int val1,int val2){
        int result=0;
        try {
            result=(val1%val2);
        }catch (ArithmeticException e){
            System.out.println("程序出现异常，求余数时除数不能为0。");
        }
            return result;
    }

    public static int scheme_remainder(int val1,int val2){
        int result=0;
        try {
            result=(val1%val2);
        }catch (ArithmeticException e){
            System.out.println("程序出现异常，求余数时除数不能为0。");
        }
        while ((result<0&&val1>0)||(result>0&&val1<0)){
            result-=val2;
        }
        return result;

    }
    public static boolean scheme_eq(double x,double y){
        return x==y;

    }

    public static boolean scheme_lt(double x,double y){
        return x<y;

    }

    public static boolean scheme_gt(double x,double y){
        return x>y;

    }

    public static boolean scheme_le(double x,double y){
        return x<=y;

    }

    public static boolean scheme_ge(double x,double y){
        return x>=y;
    }

    public static boolean scheme_evenp(int x,int y){
        return x % 2 == 0;
    }

    public static boolean scheme_oddp(int x,int y){
        return x % 2 == 1;
    }

//不使用Cons单元的数据结构称为原子（atom）。数字，字符，字符串，向量和空表’()都是原子。
    public static <T> boolean scheme_atomp(T x){
        return (scheme_numberp(x))||(scheme_booleanp(x))||(scheme_symbolp(x))||(scheme_nullp(x));
    }
//执行代码段
    public static void scheme_display(String s) throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        Object result = engine.eval(s);

    }

    public static <T> void scheme_print(T x){
        System.out.println((String) x);
    }

    public static void scheme_newline(){
        System.out.println();
    }

    public static void scheme_error(String msg){
        SchemeError error=new SchemeError(msg);
        error.print();
    }

    public void scheme_exit() throws Throwable {
        this.finalize();
    }








}
