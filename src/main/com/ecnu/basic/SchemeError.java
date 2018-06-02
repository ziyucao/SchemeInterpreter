package com.ecnu.basic;

/**
 * @author CaoZiyu
 */
public class SchemeError extends AbstractSchemeToken<String> {

    public SchemeError(String content) {
        this.content = content;
    }

    @Override
    public TokenType getType() {
        return TokenType.InvalidCharacter;
    }
}
