package com.ecnu.fa.element;

import com.ecnu.fa.element.State;
import com.ecnu.fa.element.Transition;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.LinkedList;

/**
 * State Tester.
 *
 * @author CaoZiyu
 * @version 1.0
 * @since <analyze>May 30, 2018</analyze>
 */
public class StateTest
{
    State s0;
    State s1;
    Transition t0;

    @Before
    public void before() throws Exception
    {
        s0 = new State(0, false);
        s1 = new State(1, true);
        t0 = new Transition(0, 1, 'a');
    }

    @After
    public void after() throws Exception
    {
    }

    /**
     * Method: getStateNumber()
     */
    @Test
    public void testGetStateNumber() throws Exception
    {
        Assert.assertEquals(0, s0.getStateNumber());
        Assert.assertEquals(1, s1.getStateNumber());
    }

    /**
     * Method: setStateNumber(int stateNumber)
     */
    @Test
    public void testSetStateNumber() throws Exception
    {
        s0.setStateNumber(2);
        Assert.assertEquals(2, s0.getStateNumber());
    }

    /**
     * Method: isAcceptedState()
     */
    @Test
    public void testIsAcceptedState() throws Exception
    {
        Assert.assertEquals(false, s0.isAcceptedState());
        Assert.assertEquals(true, s1.isAcceptedState());
    }

    /**
     * Method: setIsAcceptedState(boolean isAcceptedState)
     */
    @Test
    public void testSetIsAcceptedState() throws Exception
    {
        s0.setIsAcceptedState(true);
        Assert.assertEquals(true, s0.isAcceptedState());
    }

    /**
     * Method: getTransitions()
     */
    @Test
    public void test_get_and_set_transitions() throws Exception
    {
        Assert.assertTrue(s0.getTransitions().isEmpty());
        LinkedList<Transition> transitions = new LinkedList<>();
        transitions.add(t0);
        s0.setTransitions(transitions);
        Assert.assertEquals(transitions, s0.getTransitions());
    }

    /**
     * Method: addTransition(Transition t)
     */
    @Test
    public void testAddTransition() throws Exception
    {
        s0.addTransition(t0);
        LinkedList<Transition> transitions = s0.getTransitions();
        Assert.assertEquals(1, transitions.size());
        Assert.assertEquals(t0, transitions.getFirst());
    }

    /**
     * Method: getTransition(char trans)
     */
    @Test
    public void testGetTransition() throws Exception
    {
        s0.addTransition(t0);
        Assert.assertEquals(t0, s0.getTransition('a'));
        Assert.assertNull(s1.getTransition('a'));
    }


} 
