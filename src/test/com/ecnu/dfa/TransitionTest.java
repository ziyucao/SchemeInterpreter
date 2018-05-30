package com.ecnu.dfa;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * Transition Tester.
 *
 * @author CaoZiyu
 * @version 1.0
 * @since <pre>May 30, 2018</pre>
 */
public class TransitionTest
{
    Transition t;
    @Before
    public void before() throws Exception
    {
        t = new Transition(0, 3, '*');
    }

    @After
    public void after() throws Exception
    {
    }

    /**
     * Method: getTrans()
     */
    @Test
    public void testGetTrans() throws Exception
    {
        Assert.assertEquals('*', t.getTrans());
    }

    /**
     * Method: setTrans(char trans)
     */
    @Test
    public void testSetTrans() throws Exception
    {
        t.setTrans('+');
        Assert.assertEquals('+', t.getTrans());
    }

    /**
     * Method: getFromState()
     */
    @Test
    public void testGetFromState() throws Exception
    {
        Assert.assertEquals(0, t.getFromState());
    }

    /**
     * Method: setFromState(int fromState)
     */
    @Test
    public void testSetFromState() throws Exception
    {
        t.setFromState(2);
        Assert.assertEquals(2, t.getFromState());
    }

    /**
     * Method: getToState()
     */
    @Test
    public void testGetToState() throws Exception
    {
        Assert.assertEquals(3, t.getToState());
    }

    /**
     * Method: setToState(int toState)
     */
    @Test
    public void testSetToState() throws Exception
    {
        t.setToState(0);
        Assert.assertEquals(0, t.getToState());
    }


} 
