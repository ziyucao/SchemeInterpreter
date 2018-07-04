package com.ecnu.utils.Procedure;

import com.ecnu.utils.Token;

public class SymbolProcedure extends Procedure {

//    如(define a 2)中
//        value 2-》SymbolProcedure存储
//    使symbol和SymbolProcedure（value）绑定
    Token value;
    public SymbolProcedure(Token token){
        this.value=token;
    }

    public Token getValue() {
        return value;
    }
}
