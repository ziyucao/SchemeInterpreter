package com.ecnu.fa;

import com.ecnu.fa.element.State;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * FA Tester.
 *
 * @author CaoZiyu
 * @version 1.0
 * @since <analyze>May 30, 2018</analyze>
 */
public class FATest
{

    @Before
    public void before() throws Exception
    {

    }

    @After
    public void after() throws Exception
    {
    }

    /**
     * 确定fa中states按照stateNumber升序
     */
    @Test
    public void if_state_added_is_in_ascending_order_of_state_number() throws Exception
    {
        FA fa = new FA();
        State s0 = new State(0, false);
        State s2 = new State(2, false);
        State s1 = new State(1, false);
        fa.addState(s0);
        fa.addState(s2);
        fa.addState(s1);
        Assert.assertEquals(s0.getStateNumber(), fa.getState(0).getStateNumber());
        Assert.assertEquals(s1.getStateNumber(), fa.getState(1).getStateNumber());
        Assert.assertEquals(s2.getStateNumber(), fa.getState(2).getStateNumber());
    }

} 
