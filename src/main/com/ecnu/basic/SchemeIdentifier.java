package com.ecnu.basic;

/**
 * @author CaoZiyu
 */
public class SchemeIdentifier extends AbstractSchemeToken<String>
{
    public SchemeIdentifier(String content)
    {
        this.content = content;
    }

    @Override
    public TokenType getType()
    {
        return TokenType.Identifier;
    }

    public String toString(){
        return content;
    }
}
