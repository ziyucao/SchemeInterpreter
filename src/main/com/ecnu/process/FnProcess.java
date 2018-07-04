package com.ecnu.process;

import com.ecnu.primitive.primitive;

import static com.ecnu.primitive.primitive.*;

public class FnProcess {
    public static Object fnprocess(String fnname, Object[] args) {
        Object rtn = null;
        switch (fnname) {
            case "scheme_add":
                rtn = scheme_add(args);
                break;
            case "scheme_sub":
                rtn = scheme_sub(args);
                break;
            case "scheme_mul":
                rtn = scheme_mul(args);
                break;
            case "scheme_div":
                rtn = scheme_div(args);
                break;
            case "scheme_booleanp":
                rtn = primitive.scheme_booleanp(args[0]);
                break;
        }

        return rtn;
    }
}
