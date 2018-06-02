package com.ecnu.basic;

public class SchemeParenthesis extends SchemeToken<Character>
{
    public static final byte LEFT_PARENTHESIS = 0;
    public static final byte RIGHT_PARENTHESIS = 1;
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


    public SchemeParenthesis(Character content)
    {
        this.content = content;
    }

    @Override
    public TokenType getType()
    {
        return TokenType.Parenthesis;
    }
}