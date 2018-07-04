package com.ecnu.basic;

/**
 * @author CaoZiyu
 */
public class SchemeBoolean extends AbstractSchemeToken<Boolean> {

    public SchemeBoolean(Boolean content)
    {
        this.content = content;
    }

    @Override
    public TokenType getType()
    {
        return TokenType.Boolean;
    }

    public String toString(){
        if(content){
            return "#t";
        }
        return "#f";
    }
}
