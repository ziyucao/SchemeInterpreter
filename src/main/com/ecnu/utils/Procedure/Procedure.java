package com.ecnu.utils.Procedure;

import com.ecnu.utils.Frame;
import com.ecnu.utils.Pair;

import java.util.ArrayList;

import static com.ecnu.process.Eval.scheme_apply;

public class Procedure {

    public  int eval_call(Pair operands, Frame env)
    {
        //对每一个操作数进行evaluate
        //TODO:想办法迭代operands，这里rest的类型可能不对
        Pair rest = operands.map(item -> scheme_eval(item,env));
        return scheme_apply(this,rest,env);
    }

}