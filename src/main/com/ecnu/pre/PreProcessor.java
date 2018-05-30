package pre;

import java.util.ArrayList;

public class PreProcessor {

    public static ArrayList preProcessor(String input)
    {
        //deal with comments
        String noComment = input.split(";")[0];

        //deal with "(" and ")" and split to tokens
        String replaceLeftParenthese = noComment.replaceAll("(", " ( ");
        String replaceRightParenthese = replaceLeftParenthese.replaceAll(")", " ) ");
        String[] tokens = replaceRightParenthese.split("\n|\r|\t| ");

        //delete empty strings in "tokens"
        ArrayList result = new ArrayList();
        for(String token : tokens) {
            if (token == null || !token.equals(""))
            {
                result.add(token);
            }
        }

        return result;
    }

}