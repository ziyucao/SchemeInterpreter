package com.ecnu.fa;

import com.ecnu.fa.element.State;
import com.ecnu.fa.element.StateComparator;
import com.ecnu.fa.element.Transition;

import java.util.ArrayList;

/**
 * @author CaoZiyu
 */
public class FA {

    private ArrayList<State> states;

    public FA(){
        states = new ArrayList<>();
    }

    public ArrayList<State> getStates() {
        return states;
    }

    public void setStates(ArrayList<State> states) {
        this.states = states;
    }

    public void addState(State s) {
        this.states.add(s);
        states.sort(new StateComparator());
    }

    public State getState(int stateNumber) {
        return this.states.get(stateNumber);
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