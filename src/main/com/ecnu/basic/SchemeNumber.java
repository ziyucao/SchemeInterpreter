package com.ecnu.basic;

/**
 * @author CaoZiyu
 */
public class SchemeNumber extends AbstractSchemeToken<String>
{
    public SchemeNumber(String content)
    {
        this.content = content;
    }

    @Override
    public TokenType getType()
    {
        return TokenType.Number;
    }
}
