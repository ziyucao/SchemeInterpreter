package com.ecnu.utils;

import com.ecnu.basic.AbstractSchemeToken;
import com.ecnu.basic.SchemeList;
import com.ecnu.basic.SchemeParenthesis;
import com.ecnu.basic.TokenType;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    private List<TreeNode> next;

    private String name;
    public TreeNode(List<AbstractSchemeToken> schemeList){
        next = new ArrayList<>();
        name = buildTree(schemeList,next);
    }

    private TreeNode(AbstractSchemeToken token){
        next = null;
        name = token.toString();
    }
    private String buildTree(List<AbstractSchemeToken> schemeList, List<TreeNode> next){
        int parenthesis = 0;
        List<AbstractSchemeToken> nextList = new ArrayList<>();
        String name = "";
        for(AbstractSchemeToken token : schemeList){
            name += token.toString()+" ";
            if(token.getType().equals(TokenType.Parenthesis)&&(((SchemeParenthesis)token).getParenthesisType()==SchemeParenthesis.LEFT_PARENTHESIS)){
                parenthesis ++;
                if(parenthesis==2){
                    nextList.clear();
                }
                if(parenthesis>=2) {
                    nextList.add(token);
                }
                continue;
            }
            if(token.getType().equals(TokenType.Parenthesis)&&(((SchemeParenthesis)token).getParenthesisType()==SchemeParenthesis.RIGHT_PARENTHESIS)){
                parenthesis --;
                nextList.add(token);
                if(parenthesis==1){
                    next.add(new TreeNode(nextList));
                }
                continue;
            }
            if(parenthesis == 1){
                next.add(new TreeNode(token));
            }
            else {
                nextList.add(token);
            }
        }
        return name;
    }

    public String getName(){
        return this.name;
    }
    public List<TreeNode> getNext(){
        return next;
    }

    public JsonObject toJson(){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name",name);
        if(next==null){
            jsonObject.addProperty("value",0);
        }
        else{
            JsonArray jsonArray = new JsonArray();
            for(TreeNode treeNode:next){
                jsonArray.add(treeNode.toJson());
            }
            jsonObject.add("children",jsonArray);
        }
        return jsonObject;
    }
}
