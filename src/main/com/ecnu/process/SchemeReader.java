package com.ecnu.process;

import com.ecnu.Exception.EOFError;
import com.ecnu.Exception.SyntaxError;
import com.ecnu.basic.AbstractSchemeToken;
import com.ecnu.basic.SchemeList;
import com.ecnu.basic.SchemeParenthesis;
import com.ecnu.basic.TokenType;
import com.ecnu.utils.*;

public class SchemeReader {
    public static AbPair scheme_read(SchemeList src) throws Exception {
        if (src.getCurrent() == null) {
            throw new EOFError("EOFError");
        }
        AbstractSchemeToken val = src.remove_front();
        if (val.getType().equals(TokenType.Identifier) && val.getContent().equals("nil")) {
            return nil.getInstance();
        } else if (val.getType().equals(TokenType.Parenthesis) && ((SchemeParenthesis) (val)).getParenthesisType() == SchemeParenthesis.LEFT_PARENTHESIS) {
            return read_tail(src);
        } else if (val.getType().equals(TokenType.Quoted)) {
            return new Token(val);
        } else if (!CompareDelimiter.isDelimiter(val.toString())) {
            return new Token(val);
        } else {
            throw new SyntaxError("unexpected token: " + val.toString());
        }
    }

    public static AbPair read_tail(SchemeList src) throws Exception {
        try {
            if (src.getCurrent() == null) {
                throw new SyntaxError("unexpected end of file");
            } else if (src.getCurrent().getType().equals(TokenType.Parenthesis) && ((SchemeParenthesis) (src.getCurrent())).getParenthesisType() == SchemeParenthesis.RIGHT_PARENTHESIS) {
                src.remove_front();
                return nil.getInstance();
            } else if (src.getCurrent().getContent().equals(".")) {
                src.remove_front();
                AbPair abPair = scheme_read(src);
                if (read_tail(src).equals(nil.getInstance())) {
                    return abPair;
                } else {
                    throw new SyntaxError("");
                }
            } else {
                return new Pair(scheme_read(src), read_tail(src));
            }
        } catch (EOFError e) {
            throw new SyntaxError("unexpected end of file");
        }
    }
}
