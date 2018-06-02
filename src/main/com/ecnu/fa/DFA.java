package com.ecnu.fa;

import com.ecnu.fa.element.State;
import com.ecnu.fa.element.Transition;

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
