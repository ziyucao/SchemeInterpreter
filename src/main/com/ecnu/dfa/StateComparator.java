package com.ecnu.dfa;

import java.util.Comparator;

/**
 * @author CaoZiyu
 */
public class StateComparator implements Comparator<State> {

    @Override
    public int compare(State s1, State s2) {
        if (s1.getStateNumber() < s2.getStateNumber()) {
            return -1;
        } else {
            return 1;
        }
    }
}