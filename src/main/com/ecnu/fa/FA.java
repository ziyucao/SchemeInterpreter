package com.ecnu.fa;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author CaoZiyu
 */
public class FA {
    private ArrayList<State> states;

    FA(){
        states = new ArrayList<>();
    }

    public void addState(State s) {
        this.states.add(s);
        states.sort(new StateComparator());
    }

    public State getState(int stateNumber) {
        return this.states.get(stateNumber);
    }


}