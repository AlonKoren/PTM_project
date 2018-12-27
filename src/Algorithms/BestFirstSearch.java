package Algorithms;

import java.util.PriorityQueue;

public class BestFirstSearch <Problem,Solution> extends CommonSearcher <Problem,Solution>
{

    public BestFirstSearch(BackTrace<Problem, Solution> trace)
    {
        super(trace);
    }

    @Override
    protected State<Problem> popOpenList()
    {
        return ((PriorityQueue<State<Problem>>)this.openList).poll();
    }

    @Override
    protected void addToOpenList(State<Problem> state)
    {
        ((PriorityQueue<State<Problem>>)this.openList).add(state);
    }

    @Override
    protected void newSearcher()
    {
        this.openList=new PriorityQueue<>();
    }

    @Override
    protected boolean isByPriority() {
        return true;
    }
}
