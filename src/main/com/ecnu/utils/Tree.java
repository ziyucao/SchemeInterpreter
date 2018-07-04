package com.ecnu.utils;

import com.ecnu.basic.SchemeList;
import com.google.gson.JsonObject;
import javafx.util.Pair;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class Tree {
    private TreeNode root;

    public Tree(SchemeList schemeList){
        buildTree(schemeList);
    }

    public void buildTree(SchemeList schemeList){

        root = new TreeNode(schemeList.getNowList());
    }

    public TreeNode getTree(){
        return root;
    }

    public String printTree(){
        Queue<Pair<TreeNode,Integer>> queue = new ArrayBlockingQueue<Pair<TreeNode,Integer>>(1000);
        queue.add(new Pair<>(root,0));
        int row = -1;
        String result = "";
        while(!queue.isEmpty()){
            Pair<TreeNode,Integer> node = queue.poll();
            TreeNode treeNode = node.getKey();
            if(node.getValue().equals(new Integer(row+1))){
                if(row>-1) {
                    result += '\n';
                }
                row++;
            }
            result += treeNode.getName() + " ";
            if(treeNode.getNext()==null){
                continue;
            }
            for(TreeNode next : treeNode.getNext()){
                queue.add(new Pair<TreeNode,Integer>(next,node.getValue()+1));
            }
        }
        return result;
    }

    public JsonObject toJson(){
        return root.toJson();
    }
}
