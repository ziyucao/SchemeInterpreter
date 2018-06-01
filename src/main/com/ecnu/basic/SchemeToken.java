package com.ecnu.basic;

public abstract class SchemeToken<T>
{
    protected T content;

    public T getContent()
    {
        return content;
    }

    public abstract TokenType getType();

}
