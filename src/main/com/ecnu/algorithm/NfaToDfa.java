package com.ecnu.algorithm;

import com.ecnu.fa.*;
import com.ecnu.fa.element.State;
import com.ecnu.fa.element.StateSet;
import com.ecnu.fa.element.Transition;

import java.util.LinkedList;
import java.util.List;

public class NfaToDfa {

    /**
     * In this class, the "null" characters(means null transition) are all replaced by 'n',
     *  not the Greek letter "Epsilon".
     */

    /**
     * @param nfa the nfa from RE
     * @return dfa
     */
    public static FA transform(FA nfa)
    {
        FA dfa = new FA();
        List<StateSet> ssList = new LinkedList<>();
        State start = nfa.getState(0);
        ssList.add(get_null_closure(nfa, start.getStateNumber()));
        int stateSum = 0;
        int stateIndex = 0;
        while (stateSum <= ssList.size() && stateIndex < ssList.size())
        {
            stateSum = ssList.size();
            StateSet ss = ssList.get(stateIndex);

            State newS = new State(stateIndex, ss.isAccepted());
            List<Character> by = new LinkedList<>();
            for (int i = 0; i < ss.size(); i++)
            {
                LinkedList<Transition> trans = ss.get(i).getTransitions();
                for (int r = 0; r < trans.size(); r++)
                {
                    char transC = trans.get(r).getTrans();
                    if (transC != 'n' && !by.contains(transC))
                    {
                        by.add(transC);
                    }
                }
            }
            for (char c: by)
            {
                StateSet to = state_set_transform_only_once(ss, c, nfa);
                to = get_all_null_closure(to, nfa);
                if (!ssList.contains(to))
                {
                    ssList.add(to);
                    Transition t = new Transition(stateIndex, ssList.size() - 1, c);
                    newS.addTransition(t);
                }
                else
                {
                    int toId = ssList.indexOf(to);
                    Transition t = new Transition(stateIndex, toId, c);
                    newS.addTransition(t);
                }
            }
            dfa.addState(newS);

            stateIndex++;
        }



        return dfa;
    }

    /**
     * calculate the Kleene-closure of the state by id(the argument 'sId')
     * @param nfa nfa
     * @param sId the id of state
     * @return Kleene-closure
     */
    private static StateSet get_null_closure (FA nfa, int sId)
    {
        StateSet null_closure = new StateSet();
        State s = nfa.getState(sId);
        null_closure.addState(s);
        int last_sum = null_closure.size();
        null_closure.and(state_transform_only_once(s, 'n', nfa));


        while (last_sum < null_closure.size())
        {
            last_sum = null_closure.size();

            for (int i = 0; i < last_sum; i++)
            {
                State from = null_closure.get(i);
                null_closure.and(state_transform_only_once(from, 'n', nfa));
            }
        }

        return null_closure;
    }

    /**
     * calculate the Kleene-closures of the states in the state set
     * @param ss the state set
     * @param nfa nfa
     * @return Kleene-closures
     */
    private static StateSet get_all_null_closure(StateSet ss, FA nfa)
    {
        StateSet null_closure = new StateSet();
        if (ss == null)
        {
            return null_closure;
        }
        for (int i = 0; i < ss.size(); i++)
        {
            null_closure.and(get_null_closure(nfa, ss.get(i).getStateNumber()));
        }
        return null_closure;
    }

    /**
     * calculate the states that the given state can be transformed to by one character
     * @param s the given state
     * @param c the character
     * @param nfa nfa
     * @return state set
     */
    private static StateSet state_transform_only_once(State s, char c, FA nfa)
    {
        StateSet ss = new StateSet();
        LinkedList<Transition> trans = s.getTransitions();
        if (trans != null && trans.size() != 0)
        {
            for (int i = 0; i < trans.size(); i++)
            {
                if (trans.get(i).getTrans() == c)
                {
                    State to = nfa.getState(trans.get(i).getToState());
                    if (!ss.contains(to))
                    {
                        ss.addState(to);
                    }
                }
            }
        }
        return ss;
    }

    /**
     * calculate the state that the given states can be transformed to by one character
     * @param from the state set
     * @param c the character
     * @param nfa nfa
     * @return state set
     */
    private static StateSet state_set_transform_only_once(StateSet from, char c, FA nfa)
    {
        StateSet to = new StateSet();
        if (from != null && from.size() != 0)
        {
            for (int i = 0; i < from.size(); i++)
            {
                State s = from.get(i);
                to.and(state_transform_only_once(s, c, nfa));
            }
        }
        return to;
    }

}