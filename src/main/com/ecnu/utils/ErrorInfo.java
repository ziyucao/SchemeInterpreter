package com.ecnu.utils;

import com.google.gson.JsonObject;

import javax.xml.ws.EndpointReference;

public class ErrorInfo {
    private static String ErrorInfo = "";
    private static int position = -1;
    public static void setError(String error){
        if(!ErrorInfo.equals("")) {
            ErrorInfo += '\n'+error;
        }
        else{
            ErrorInfo+=error;
        }
    }
    public static String getErrorInfo(){
        return ErrorInfo;
    }

    public static void setPosition(int pos){
        position = pos;
    }
    public static int getErrorPosition(){
        return position;
    }
    public static void clean(){
        ErrorInfo = "";
        position = -1;
    }

    public static void toJson(JsonObject result){
        if(ErrorInfo.equals("")){
            result.addProperty("SchemeResult","Accept");
        }
        else{
            result.addProperty("SchemeResult","Error");
            result.addProperty("ErrorInfo",ErrorInfo);
            result.addProperty("ErrorPosition",position);
        }
    }
}
