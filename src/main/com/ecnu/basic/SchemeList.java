package com.ecnu.basic;

import java.util.List;

/**
 * @author CaoZiyu
 */
public class SchemeList
{
    private List<AbstractSchemeToken> tokenList;


    public SchemeList(List<AbstractSchemeToken> tokenList)
    {
        this.tokenList = tokenList;
    }

    public List<AbstractSchemeToken> getTokenList()
    {
        return tokenList;
    }

    public void setTokenList(List<AbstractSchemeToken> tokenList)
    {
        this.tokenList = tokenList;
    }

    public void print() {
        for (AbstractSchemeToken st: tokenList) {
            System.out.println(st.getContent() + " " + st.getType().toString());
        }
    }
}
