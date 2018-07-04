package com.ecnu.basic;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author CaoZiyu
 */
public class SchemeList
{
    private List<AbstractSchemeToken> tokenList;
    private List<AbstractSchemeToken> nowList;
    private static List<String> tokenListString;

    private int index;
    private List<AbstractSchemeToken> current_lines;
    public SchemeList(List<AbstractSchemeToken> tokenList,ArrayList<String> tokenListString)
    {
        this.nowList = null;
        index = 0;
        this.tokenList = tokenList;
        this.current_lines = new ArrayList<AbstractSchemeToken>(tokenList);
        this.tokenListString=new ArrayList<String>(tokenListString);
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
            AbstractSchemeToken token = current_lines.get(0);
            if(nowList!=null) {
                nowList.add(token);
            }
            current_lines.remove(0);
        }
        return tokenList.get(index++);
    }
    public static int findByToken(String str){
        int index = tokenListString.indexOf(str);
        return index;
    }

    public void setNowList(List<AbstractSchemeToken> nowList)
    {
        this.nowList = nowList;
    }

    public List<AbstractSchemeToken> getNowList(){
        return this.nowList;
    }
    public boolean more_on_lines(){
        return index<tokenList.size();
    }

    public void print() {
        for (AbstractSchemeToken st: tokenList) {
            System.out.println(st.getContent() + " " + st.getType().toString());
        }
    }

    public void print_(JsonObject result) {
        JsonArray jsonArray = new JsonArray();
        for (AbstractSchemeToken st: tokenList) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("str",st.getContent().toString());
            jsonObject.addProperty("type",st.getType().toString());
            jsonArray.add(jsonObject);
        }
        result.add("TokenList",jsonArray);
    }

    public boolean isEmpty(){
        return current_lines.isEmpty();
    }
}
