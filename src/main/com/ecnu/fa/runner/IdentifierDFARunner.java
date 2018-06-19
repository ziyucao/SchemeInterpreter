package com.ecnu.fa.runner;

import com.ecnu.fa.DFA;
import com.ecnu.fa.element.State;
import static com.ecnu.algorithm.CharacterChecker.isLetter;

/**
 * @author ddl
 */
public class IdentifierDFARunner extends DFARunner {

    public IdentifierDFARunner(DFA dfa)
    {
        super(dfa);
    }

    public IdentifierDFARunner(DFA dfa, String input)
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
            if (isLetter(c))
            {
                c = '0';
            }
            stateNumber = dfa.goToNext(state, c);
            if (stateNumber >= 0) {
                state = getDFa().getState(stateNumber);
                // for debug
//                System.out.print("i "+state.getStateNumber()+" ");
            }
            else {
                return -1;
            }
        }
        return stateNumber;
    }
}
