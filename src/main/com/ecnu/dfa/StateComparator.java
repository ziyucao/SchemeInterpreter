package com.ecnu.dfa;

import java.util.Comparator;

public class StateComparator implements Comparator {
    
    @Override
    public int compare(Object o1, Object o2) {
        State s1 = (State) o1;
        State s2 = (State) o2;
        if (s1.getStateNumber() < s2.getStateNumber()) {
            return 1;
        } else {
            return 0;
        }
    }
}