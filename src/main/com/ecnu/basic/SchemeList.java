package com.ecnu.basic;

import com.sun.tools.javac.util.List;

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
}
