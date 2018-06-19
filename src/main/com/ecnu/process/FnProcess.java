package com.ecnu.process;

import java.util.ArrayList;

import static com.ecnu.primitive.primitive.*;

public class FnProcess {
    public static int fnprocess (String fnname, int[] args)
    {
        int rtn = 0 ;
        switch (fnname)
        {
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
        }
        return rtn;
    }
}
