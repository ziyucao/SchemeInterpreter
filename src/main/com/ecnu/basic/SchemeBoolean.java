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
            return "True";
        }
        return "False";
    }
    @Override
    public boolean equles(Object obj){
        if((obj instanceof SchemeBoolean)){
            SchemeBoolean o= (SchemeBoolean) obj;
            return o.getContent().equals(this.content);
        }
        return false;
    }
}
