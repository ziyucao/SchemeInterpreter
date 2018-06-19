package com.ecnu.basic;

/**
 * @author CaoZiyu
 */
public class SchemeParenthesis extends AbstractSchemeToken<Character>
{
    public static final byte LEFT_PARENTHESIS = 0;
    public static final byte RIGHT_PARENTHESIS = 1;

    public SchemeParenthesis(Character content)
    {
        this.content = content;
    }

    public int getParenthesisType() {
        if (this.content.charValue() == '(') {
            return LEFT_PARENTHESIS;
        }
        else if (this.content.charValue() == ')') {
            return RIGHT_PARENTHESIS;
        }
        else {
            return -1;
        }
    }
    @Override
    public TokenType getType()
    {
        return TokenType.Parenthesis;
    }

    public String toString(){
        if(getParenthesisType() == LEFT_PARENTHESIS){
            return "(";
        }
        else{
            return ")";
        }
    }
}