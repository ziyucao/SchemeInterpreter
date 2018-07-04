package com.ecnu.fa.runner;

import com.ecnu.fa.DFA;
import com.ecnu.fa.element.State;
import static com.ecnu.algorithm.CharacterChecker.isNumber;

/**
 * @author ddl
 */
public class DigitDFARunner extends DFARunner{

    public DigitDFARunner(DFA dfa) {
        super(dfa);
    }

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
            case 2: digit = '2'; break;
            case 8: digit = '3'; break;
            case 10: digit = '4'; break;
            case 16: digit = '5'; break;
            default: return -1;
        }
        for (char c : inputCharSeq) {
            if (isNumber(c, base))
            {
                c = digit;
            }
            stateNumber = dfa.goToNext(state, c);
            if (stateNumber >= 0) {
                state = getDFa().getState(stateNumber);
            }
            else {
                return -1;
            }
        }
        return stateNumber;
    }
}
