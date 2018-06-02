package com.ecnu.basic;

/**
 * @author CaoZiyu
 */
public class SchemeNumber extends AbstractSchemeToken<Double>
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
