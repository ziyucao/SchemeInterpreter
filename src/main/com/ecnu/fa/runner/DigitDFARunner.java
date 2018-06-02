package com.ecnu.fa.runner;

import com.ecnu.fa.DFA;
import com.ecnu.fa.element.State;

public class DigitDFARunner extends DFARunner{

    public DigitDFARunner(DFA dfa, String input) {
        super(dfa, input);
    }

    public int run(int base)
    {
        char[] inputCharSeq = input.toCharArray();
        State state = dfa.getState(0);
        int stateNumber = 0;
        char digit;
        switch (base)
        {
            case 2: c = '2'; break;
            case 8: c = '3'; break;
            case 10: c = '4'; break;
            case 16: c = '5'; break;
            default: return -1;
        }
        for (char c : inputCharSeq) {
            if (is)
            stateNumber = dfa.goToNext(state, c);
            if (stateNumber >= 0) {
                state = getDFa().getState(stateNumber);
                // for debug
                System.out.print(""+state.getStateNumber()+" ");
            }
            else {
                return -1;
            }
        }
        return stateNumber;
    }
}
