package com.ecnu.analyze;

import java.util.ArrayList;

/**
 * @author ddl
 */
public class PreProcessor {

    public static ArrayList preProcess(String input)
    {
        if (isEmpty(input))
        {
            return new ArrayList();
        }

        //deal with comments
        String noComment = input.split(";")[0];

        //deal with "(" and ")" and split to tokens
        if (isEmpty(noComment)) {
            return new ArrayList();
        }
        String replaceLeftParenthese = noComment.replaceAll("\\(", " ( ");
        String replaceRightParenthese = replaceLeftParenthese.replaceAll("\\)", " ) ");
        String[] tokens = replaceRightParenthese.split("\n|\r|\t| ");

        //delete empty strings in "tokens"
        ArrayList result = new ArrayList();
        for(String token : tokens) {
            if (!isEmpty(token))
            {
                result.add(token);
            }
        }

        return result;
    }

    private static boolean isEmpty(String input)
    {
        if (input == null || input.isEmpty()) {
            return true;
        }
        else {
            return false;
        }
    }

}