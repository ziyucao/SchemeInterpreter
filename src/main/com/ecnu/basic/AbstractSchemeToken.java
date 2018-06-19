package com.ecnu.basic;

/**
 * @author CaoZiyu
 */
public abstract class AbstractSchemeToken<T>
{
    protected T content;

    public T getContent()
    {
        return content;
    }

    public abstract TokenType getType();

}
