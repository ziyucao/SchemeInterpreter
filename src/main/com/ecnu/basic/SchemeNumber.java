package com.ecnu.basic;

public class SchemeNumber extends SchemeToken<Double>
{
    public SchemeNumber(Double content)
    {
        this.content = content;
    }

    @Override
    public TokenType getType()
    {
        return TokenType.Number;
    }
}
