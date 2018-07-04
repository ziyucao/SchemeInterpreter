package com.ecnu.utils;

import com.ecnu.basic.AbstractSchemeToken;

public class Token extends AbPair {
    AbstractSchemeToken token;
    public Token(AbstractSchemeToken token){
        this.token = token;
    }

    public AbstractSchemeToken getToken()
    {
        return token;
    }

    public String toString(){
        return token.toString();
    }

    public String print(){
        return token.toString();
    }
}
