package com.ecnu.basic;

public class SchemeNumber extends SchemeToken<Integer>
{
    public SchemeNumber(Integer content)
    {
        this.content = content;
    }

    @Override
    public TokenType getType()
    {
        return TokenType.Number;
    }
}
