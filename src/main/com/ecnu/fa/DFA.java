package com.ecnu.fa;

import com.ecnu.algorithm.NfaToDfa;
import com.ecnu.fa.FA;
import com.ecnu.fa.State;
import com.ecnu.fa.Transition;

public class DFA extends FA {
    public int goToNext(State s, char c) {
        if (s!=null) {
            Transition t = s.getTransition(c);
            if (t!=null) {
                return t.getToState();
            }
        }
        return -1;
    }

    public DFA(FA fa) {
        super();
        this.setStates(NfaToDfa.transform(fa).getStates());
    }
}
