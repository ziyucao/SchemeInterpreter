package com.ecnu.dfa;

import java.util.LinkedList;

/**
 * @author CaoZiyu
 */
public class State {
    private int stateNumber;
    private boolean isAcceptedState;
    private LinkedList<Transition> transitions;
    
    State(int stateNumber, boolean isAcceptedState) {
        this.stateNumber = stateNumber;
        this.isAcceptedState = isAcceptedState;
        this.transitions = new LinkedList<>();
    }
    /**
	 * @return the stateNumber
	 */
	public int getStateNumber() {
		return stateNumber;
	}
	/**
	 * @param stateNumber the stateNumber to set
	 */
	public void setStateNumber(int stateNumber) {
		this.stateNumber = stateNumber;
	}
	/**
	 * @return the isAcceptedState
	 */
	public boolean isAcceptedState() {
		return isAcceptedState;
	}
	/**
	 * @param isAcceptedState the isAcceptedState to set
	 */
	public void setIsAcceptedState(boolean isAcceptedState) {
		this.isAcceptedState = isAcceptedState;
	}
	/**
	 * @return the transitions
	 */
	public LinkedList<Transition> getTransitions() {
		return transitions;
	}
	/**
	 * @param transitions the transitions to set
	 */
	public void setTransitions(LinkedList<Transition> transitions) {
		this.transitions = transitions;
    }
    public void addTransition(Transition t) {
        this.transitions.add(t);
    }
    public Transition getTransition (char trans) {
        for (Transition t : transitions) {
            if (t.getTrans() == trans) {
                return t;
            }
        }
        return null;
    }
}