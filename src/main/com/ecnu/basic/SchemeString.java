package com.ecnu.basic;

/**
 * @author CaoZiyu
 */
public class SchemeString extends AbstractSchemeToken<String>
{
    public SchemeString(String content)
    {
        this.content = content;
    }

    @Override
    public TokenType getType()
    {
        return TokenType.String;
    }
}
