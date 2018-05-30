package com.ecnu.dfa;

import java.util.ArrayList;
import java.util.Collections;

public class FA {
    private ArrayList<State> states;

    FA(){
        states = new ArrayList<>();
    }

    public void addState(State s) {
        this.states.add(s);
        Collections.sort(states, new StateComparator());
    }

    public State getState(int StateNumber) {
        return this.states.get(StateNumber);
    }


}