package com.ecnu.basic;

public class SchemeParenthesis extends SchemeToken<Character>
{
    public static final byte LEFT_PARENTHESIS = 0;
    public static final byte RIGHT_PARENTHESIS = 1;

    private int parenthesisType;

    public SchemeParenthesis(Character content, int parenthesisType)
    {
        this.parenthesisType = parenthesisType;
        this.content = content;
    }

    @Override
    public Type getType()
    {
        return Type.Parenthesis;
    }
}