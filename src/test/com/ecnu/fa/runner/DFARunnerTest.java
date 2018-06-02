package com.ecnu.fa.runner;

import com.ecnu.algorithm.NfaToDfa;
import com.ecnu.fa.DFA;
import com.ecnu.fa.FA;
import com.ecnu.fa.element.State;
import com.ecnu.fa.element.Transition;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * DFARunner Tester.
 *
 * @author CaoZiyu
 * @version 1.0
 * @since <pre>June 3, 2018</pre>
 */
public class DFARunnerTest {

    DFARunner runner;
    DFA dfa;

    @Before
    public void before() throws Exception {
        FA nfa = new FA();

        State s0 = new State(0, false);
        s0.addTransition(new Transition(0, 1, 'n'));
        s0.addTransition(new Transition(0, 7, 'n'));
        nfa.addState(s0);

        State s1 = new State(1, false);
        s1.addTransition(new Transition(1, 2, 'n'));
        s1.addTransition(new Transition(1, 4, 'n'));
        nfa.addState(s1);

        State s2 = new State(2, false);
        s2.addTransition(new Transition(2, 3, 'a'));
        nfa.addState(s2);

        State s3 = new State(3, false);
        s3.addTransition(new Transition(3, 6, 'n'));
        nfa.addState(s3);

        State s4 = new State(4, false);
        s4.addTransition(new Transition(4, 5, 'b'));
        nfa.addState(s4);

        State s5 = new State(5, false);
        s5.addTransition(new Transition(5, 6, 'n'));
        nfa.addState(s5);

        State s6 = new State(6, false);
        s6.addTransition(new Transition(6, 7, 'n'));
        s6.addTransition(new Transition(6, 1, 'n'));
        nfa.addState(s6);

        State s7 = new State(7, true);
        nfa.addState(s7);

        dfa = new DFA(NfaToDfa.transform(nfa));
        runner = new DFARunner(dfa, "aaabbbab");
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getDFa()
     */
    @Test
    public void testGetDFa() throws Exception {
        DFA dfaAnother = new DFA(new FA());
        runner = new DFARunner(dfaAnother);
        Assert.assertEquals(dfaAnother, runner.getDFa());
    }

    /**
     * Method: setDFa(DFA dfa)
     */
    @Test
    public void testSetDFa() throws Exception {
        DFA dfaAnother = new DFA(new FA());
        runner.setDFa(dfaAnother);
        Assert.assertEquals(dfaAnother, runner.getDFa());
    }

    /**
     * Method: getInput()
     */
    @Test
    public void testGetInput() throws Exception {
        Assert.assertEquals("aaabbbab", runner.getInput());
    }

    /**
     * Method: setInput(String input)
     */
    @Test
    public void testSetInput() throws Exception {
        String input = "aabb";
        runner.setInput(input);
        Assert.assertEquals(input, runner.getInput());
    }

    /**
     * Method: run()
     */
    @Test
    public void testRun() throws Exception {
        Assert.assertEquals(2, runner.run());
        runner.setInput("aabb");
        Assert.assertEquals(2, runner.run());
        runner.setInput("abbba");
        Assert.assertEquals(1, runner.run());
        runner.setInput("aaaaa");
        Assert.assertEquals(1, runner.run());
        runner.setInput("aaacaa");
        Assert.assertEquals(-1, runner.run());
        runner.setInput("");
        Assert.assertEquals(0, runner.run());
        runner.setInput(null);
        Assert.assertEquals(-1, runner.run());
    }

    /**
     * Method: isAccepted(int stateId)
     */
    @Test
    public void testIsAccepted() throws Exception {
        Assert.assertTrue(runner.isAccepted(0));
        Assert.assertTrue(runner.isAccepted(1));
        Assert.assertTrue(runner.isAccepted(2));
        Assert.assertFalse(runner.isAccepted(-1));
        Assert.assertFalse(runner.isAccepted(3));
    }


} 
