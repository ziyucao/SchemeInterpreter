package com.ecnu.fa;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author CaoZiyu
 */
public class FA {
    private ArrayList<State> states;

    public FA(){
        states = new ArrayList<>();
    }

    public void addState(State s) {
        this.states.add(s);
        Collections.sort(states, new StateComparator());
    }

    public State getState(int StateNumber) {
        return this.states.get(StateNumber);
    }

    public void print()
    {
        for (State s : states)
        {
            for (Transition t : s.getTransitions())
            {
                System.out.println("[" + t.getFromState() + "] = " + t.getTrans() + " => [" + t.getToState() + "]");
            }
        }

        for (State s : states)
        {
            if (s.isAcceptedState())
            {
                System.out.print(s.getStateNumber() + " ");
            }
        }
        System.out.println();
    }
}