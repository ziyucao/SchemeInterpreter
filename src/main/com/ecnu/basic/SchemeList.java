package com.ecnu.basic;

import java.util.List;

public class SchemeList
{
    private List<SchemeToken> tokenList;


    public SchemeList(List<SchemeToken> tokenList)
    {
        this.tokenList = tokenList;
    }

    public List<SchemeToken> getTokenList()
    {
        return tokenList;
    }

    public void setTokenList(List<SchemeToken> tokenList)
    {
        this.tokenList = tokenList;
    }

    public void print() {
        for (SchemeToken st: tokenList) {
            System.out.println(st.getContent() + " " + st.getType().toString());
        }
    }
}
