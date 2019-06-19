package Algorithms;

import java.util.Collection;
import java.util.HashSet;


public abstract class CommonSearcher <Problem,Solution> implements Searcher <Problem,Solution>
{
    protected Collection<State<Problem>> openList;
    protected BackTrace <Problem,Solution> trace;
    public CommonSearcher(BackTrace <Problem,Solution> trace)
    {
       newSearcher();
       this.trace=trace;
    }
    protected abstract State<Problem> popOpenList();
    protected abstract void addToOpenList(State<Problem> state);

    protected abstract void newSearcher();
    protected abstract boolean isByPriority();

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
                    if(isByPriority() && openList.contains(state)&& openList.removeIf((State<Problem> sOpen) -> sOpen.equals(state) && sOpen.getCost() > state.getCost()))
                    {
                        addToOpenList(state);
                    }
                }
            }
        }
        System.err.println("openList empty");
//        throw new RuntimeException("openList empty");
        return null;
    }
}
