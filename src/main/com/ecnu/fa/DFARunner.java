package com.ecnu.fa;

public class DFARunner {
    private DFA dfa;
    private String input;

    public DFARunner(DFA dfa, String input) {
        this.dfa = dfa;
        this.input = input;
    }

    public DFA getDFa() {
        return dfa;
    }

    public void setDFa(DFA dfa) {
        this.dfa = dfa;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    //TODO : int -> Result

    public int run(){
        char[] inputCharSeq = input.toCharArray();
        State state = dfa.getState(0);
        int stateNumber = 0;
        for (char c : inputCharSeq) {
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
