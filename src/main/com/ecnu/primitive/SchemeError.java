package com.ecnu.primitive;

public class SchemeError {
    String msg="";
    public SchemeError(String msg){
        this.msg=msg;
    }
    public void print(){
        System.out.println(this.msg);
    }
//    public void print(String msg) {
//        System.out.println(msg);
//    }

}
