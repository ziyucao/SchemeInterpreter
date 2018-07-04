package com.ecnu.utils.Procedure;

import com.ecnu.utils.AbPair;
import com.ecnu.utils.Frame;
import com.ecnu.utils.Pair;

import java.util.ArrayList;

import static com.ecnu.process.Eval.scheme_apply;
import static com.ecnu.process.Eval.scheme_eval;

public class Procedure {

    public Object eval_call(AbPair operands, Frame env)
    {
        //对每一个操作数进行evaluate
        //TODO:想办法迭代operands，这里rest的类型可能不对
        AbPair rest = operands.map("scheme_eval",env);
        return scheme_apply( this,rest,env);
    }

    public Object apply(AbPair args,Frame env){
        return null;
    }

}