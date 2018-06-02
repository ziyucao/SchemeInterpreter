package com.ecnu.basic;

public class SchemeError extends SchemeToken<String>{

    public SchemeError(String content) {
        this.content = content;
    }

    @Override
    public TokenType getType() {
        return TokenType.InvalidCharacter;
    }
}
