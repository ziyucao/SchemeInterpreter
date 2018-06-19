package com.ecnu.fa.element;

import java.util.LinkedList;
import java.util.List;

/**
 * @author ddl
 */
public class StateSet {

    private List<State> states;
    private boolean isAccepted;

    public StateSet()
    {
        states = new LinkedList<>();
        isAccepted = false;
    }

    public void addState(State s)
    {
        if (!states.contains(s))
        {
            states.add(s);
            if (s.isAcceptedState())
            {
                isAccepted = true;
            }
        }
    }

    public List<State> getStates()
    {
        return states;
    }

    public State get(int index)
    {
        return states.get(index);
    }

    public int size()
    {
        return states.size();
    }

    public boolean isAccepted()
    {
        return isAccepted;
    }

    private boolean isEqual(StateSet ss)
    {
        List<State> others = ss.getStates();
        boolean isEqual = true;

        if (states.size() != others.size())
        {
            return false;
        }

        for (int i = 0; i < states.size(); i++)
        {
            if (!others.contains(states.get(i)))
            {
                isEqual = false;
                break;
            }
        }

        return isEqual;
    }

    public boolean contains(State s)
    {
        return states.contains(s);
    }

    public void and(StateSet ss)
    {
        if (ss != null)
        {
            for (int i = 0; i < ss.size(); i++) {
                State s = ss.get(i);
                if (!contains(s)) {
                    addState(s);
                }
            }
            if (ss.isAccepted())
            {
                isAccepted = true;
            }
        }
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == null)
        {
            return false;
        }
        if (o instanceof StateSet)
        {
            if (isEqual((StateSet) o))
            {
                return true;
            }
        }
        return false;
    }
}
