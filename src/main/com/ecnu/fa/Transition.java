package com.ecnu.fa;

/**
 * @author CaoZiyu
 */
public class Transition {
    
    private int fromState;
    private int toState;
    private char trans;
   
    public Transition (int fromState, int toState, char trans) {
        this.fromState = fromState;
        this.toState = toState;
        this.trans = trans;
    }
    
    /**
	 * @return the trans
	 */
	public char getTrans() {
		return trans;
	}
	/**
	 * @param trans the trans to set
	 */
	public void setTrans(char trans) {
		this.trans = trans;
	}
	/**
	 * @return the fromState
	 */
	public int getFromState() {
		return fromState;
	}
	/**
	 * @param fromState the fromState to set
	 */
	public void setFromState(int fromState) {
		this.fromState = fromState;
	}
	/**
	 * @return the toState
	 */
	public int getToState() {
		return toState;
	}
	/**
	 * @param toState the toState to set
	 */
	public void setToState(int toState) {
		this.toState = toState;
	}


}