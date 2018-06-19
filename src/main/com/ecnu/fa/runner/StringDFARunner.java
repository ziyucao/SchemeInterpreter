package com.ecnu.fa.runner;

import com.ecnu.fa.DFA;
import com.ecnu.fa.element.State;
import static com.ecnu.algorithm.CharacterChecker.isStringCharacter;

public class StringDFARunner extends DFARunner {

    /**
     * @author ddl
     */
    public StringDFARunner(DFA dfa)
    {
        super(dfa);
    }

    public StringDFARunner(DFA dfa, String input)
    {
        super(dfa, input);
    }

    @Override
    public int run()
    {
        char[] inputCharSeq = input.toCharArray();
        State state = dfa.getState(0);
        int stateNumber = 0;
        for (char c : inputCharSeq) {
            if (!isStringCharacter(c))
            {
                return -1;
            }
            else
            {
                c = '6';
            }
            stateNumber = dfa.goToNext(state, c);
            if (stateNumber >= 0) {
                state = getDFa().getState(stateNumber);
                // for debug
//                System.out.print("s "+state.getStateNumber()+" ");
            }
            else {
                return -1;
            }
        }
        return stateNumber;
    }
}
