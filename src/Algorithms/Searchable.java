package Algorithms;

import java.util.Collection;

public interface Searchable <Problem>
{
    State<Problem> getInitialState();
    boolean isGoalState(State <Problem> state);
    Collection<State <Problem>> getAllPossibleStates(State<Problem> s);
}
