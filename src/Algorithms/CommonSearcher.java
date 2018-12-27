package Algorithms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.function.Predicate;

public abstract class CommonSearcher <Problem,Solution> implements Searcher <Problem,Solution>
{
    protected Collection<State<Problem>> openList;
    private BackTrace <Problem,Solution> trace;
    public CommonSearcher(BackTrace <Problem,Solution> trace)
    {
       newSearcher();
       this.trace=trace;
    }
    protected abstract State<Problem> popOpenList();
    protected abstract void addToOpenList(State<Problem> state);

    protected abstract void newSearcher();

        @Override
    public Solution search(Searchable<Problem> s)
    {
        newSearcher();
        addToOpenList(s.getInitialState());
        HashSet<State<Problem>> closedSet= new HashSet<>();
        while(openList.size()>0)
        {
            State<Problem> n = popOpenList();// dequeue
            closedSet.add(n);

            if (s.isGoalState(n))
                return trace.backTrace(n, s.getInitialState()); // private method, back traces through the parents
            Collection<State<Problem>> successors = s.getAllPossibleStates(n);
            for (State<Problem> state : successors)
            {
                if (!closedSet.contains(state) && !openList.contains(state))
                {
                    state.setCameFrom(n);
                    addToOpenList(state);
                }
                else
                {
                    if(openList.contains(state)&& openList.removeIf((State<Problem> sOpen) -> sOpen.equals(state) && sOpen.getCost() > state.getCost()))
                    {
                        addToOpenList(state);
                    }
                }
            }
        }
        return null;
    }
}
