package com.ecnu.basic;

public class SchemeIdentifier extends SchemeToken<String>
{
    public SchemeIdentifier(String content)
    {
        this.content = content;
    }

    @Override
    public Type getType()
    {
        return Type.Identifier;
    }
}
