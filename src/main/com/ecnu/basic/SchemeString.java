package com.ecnu.basic;

public class SchemeString extends SchemeToken<String>
{
    public SchemeString(String content)
    {
        this.content = content;
    }

    @Override
    public Type getType()
    {
        return Type.String;
    }
}
