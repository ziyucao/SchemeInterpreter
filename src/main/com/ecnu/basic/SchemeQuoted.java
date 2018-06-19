package com.ecnu.basic;

/**
 * @author CaoZiyu
 */
public class SchemeQuoted extends AbstractSchemeToken<Character>
{

    public SchemeQuoted(Character content)
    {
        this.content = content;
    }

    @Override
    public TokenType getType()
    {
        return TokenType.Quoted;
    }

    public String toString(){
        return "quote";
    }
}
