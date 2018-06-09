package com.ecnu.basic;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author CaoZiyu
 */
public class SchemeList
{
    private List<AbstractSchemeToken> tokenList;

    private int index;
    private List<AbstractSchemeToken> current_lines;
    public SchemeList(List<AbstractSchemeToken> tokenList)
    {
        index = 0;
        this.tokenList = tokenList;
        this.current_lines = new ArrayList<AbstractSchemeToken>(tokenList);
    }

    public List<AbstractSchemeToken> getTokenList()
    {
        return tokenList;
    }

    public void setTokenList(List<AbstractSchemeToken> tokenList)
    {
        this.tokenList = tokenList;
    }

    public AbstractSchemeToken getCurrent(){
        if(index >= tokenList.size()){
            return null;
        }
        return tokenList.get(index);
    }

    public List<AbstractSchemeToken> getCurrent_lines(){
        return current_lines;
    }

    public AbstractSchemeToken remove_front(){
        if(current_lines.size()>0){
            current_lines.remove(0);
        }
        return tokenList.get(index++);
    }

    public boolean more_on_lines(){
        return index<tokenList.size();
    }

    public void print() {
        for (AbstractSchemeToken st: tokenList) {
            System.out.println(st.getContent() + " " + st.getType().toString());
        }
    }

    public boolean isEmpty(){
        return current_lines.isEmpty();
    }
}
