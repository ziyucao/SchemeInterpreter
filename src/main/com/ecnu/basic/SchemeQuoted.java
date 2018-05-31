package com.ecnu.basic;

public class SchemeQuoted extends SchemeToken<Character>
{

    public SchemeQuoted(Character content)
    {
        this.content = content;
    }

    @Override
    public Type getType()
    {
        return Type.Quoted;
    }
}
