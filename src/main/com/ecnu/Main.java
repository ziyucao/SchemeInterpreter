package com.ecnu;

import com.ecnu.algorithm.NfaToDfa;
import com.ecnu.fa.FA;
import com.ecnu.fa.State;
import com.ecnu.fa.Transition;

public class Main
{
    public static void main(String[] args)
    {
        FA nfa = new FA();
        FA dfa;

        State s0 = new State(0, false);
        s0.addTransition(new Transition(0, 1, 'n'));
        s0.addTransition(new Transition(0, 7, 'n'));
        nfa.addState(s0);

        State s1 = new State(1, false);
        s1.addTransition(new Transition(1, 2, 'n'));
        s1.addTransition(new Transition(1, 4, 'n'));
        nfa.addState(s1);

        State s2 = new State(2, false);
        s2.addTransition(new Transition(2, 3, 'a'));
        nfa.addState(s2);

        State s3 = new State(3, false);
        s3.addTransition(new Transition(3, 6, 'n'));
        nfa.addState(s3);

        State s4 = new State(4, false);
        s4.addTransition(new Transition(4, 5, 'b'));
        nfa.addState(s4);

        State s5 = new State(5, false);
        s5.addTransition(new Transition(5, 6, 'n'));
        nfa.addState(s5);

        State s6 = new State(6, false);
        s6.addTransition(new Transition(6, 7, 'n'));
        s6.addTransition(new Transition(6, 1, 'n'));
        nfa.addState(s6);

        State s7 = new State(7, true);
        nfa.addState(s7);

        dfa = NfaToDfa.transform(nfa);
        dfa.print();
    }
}