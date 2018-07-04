package com.ecnu.utils;

import com.ecnu.Exception.TypeError;
import com.ecnu.basic.*;
import com.ecnu.process.LexicalAnalyze;
import com.ecnu.process.PreProcessor;
import com.ecnu.process.SchemeReader;

import java.util.ArrayList;

import static com.ecnu.process.Eval.scheme_eval;

public class AbPair<T1,T2> {
    protected T1 first;
    protected T2 second;

    public AbPair(T1 first,T2 second){
        this.first = first;
        this.second = second;
    }

    public AbPair(){
        first = null;
        second = null;
    }

    public void setFirst(T1 first){
        this.first = first;
    }

    public void setSecond(T2 second){
        this.second = second;
    }

    public int getLength() throws TypeError {
        return 0;
    }

    public String print(){
        return toString();
    }
    public T1 getFirst(){
        return first;
    }
    public T2 getSecond() {
        return second;
    }


    public Object getVal() {
        if (this.getFirst() instanceof Token && ((Token) this.getFirst()).getToken() instanceof SchemeNumber) {
            return Integer.parseInt(this.getFirst().toString());
        } else if (this.getFirst() instanceof Token && ((Token) this.getFirst()).getToken() instanceof SchemeBoolean) {
            return (Token) this.getFirst();
        }
        return null;
    }

    public AbPair map(String fn, Frame env) {
        ArrayList pairList = new ArrayList<>();
        pairList.add("(");

        switch (fn) {
            case "scheme_eval":
                pairList.add(scheme_eval((AbPair) this.getFirst(), env).toString());
                AbPair second = (AbPair) this.getSecond();
                while (second instanceof Pair) {
                    pairList.add(scheme_eval((AbPair) second.getFirst(), env).toString());
                    second = (AbPair) second.getSecond();
                }

        }
        pairList.add(")");

        try {
            LexicalAnalyze lexicalAnalyze = new LexicalAnalyze();
            SchemeList schemeList = lexicalAnalyze.lexical_analyze(pairList);
            AbPair abpair =   SchemeReader.scheme_read(schemeList);
            return abpair;
        }
        catch (Exception e)
        {
            ErrorInfo.setError("handle per element fail!");
            return new AbPair();
        }

    }

    public AbstractSchemeToken getToken(){
        return new AbstractSchemeToken() {
            @Override
            public TokenType getType() {
                return null;
            }
        };
    }
}