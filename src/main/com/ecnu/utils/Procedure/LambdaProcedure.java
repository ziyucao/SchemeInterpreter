package com.ecnu.utils.Procedure;

import com.ecnu.process.Eval;
import com.ecnu.utils.AbPair;
import com.ecnu.utils.Frame;
import com.ecnu.utils.Pair;

public class LambdaProcedure extends UserDefinedProcedure {
//    Pair body;
    Frame env;
    Pair formals;
    public LambdaProcedure(AbPair formals, AbPair body, Frame env){
        this.body= (Pair) body;
        this.env=env;
        this.formals= (Pair) formals;
    }


    public Frame make_call_frame(AbPair args, Frame env){
        return env.make_child_frame(this.formals, (Pair) args);
    }
    public Object apply(AbPair args, Frame env){
        Frame new_env=this.make_call_frame(args,env);
        return Eval.eval_all(this.body,new_env);
    }
}
