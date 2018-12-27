package Algorithms;

import java.util.LinkedList;
import java.util.Queue;

public class BFS <Problem,Solution> extends CommonSearcher <Problem,Solution>
{
    public BFS(BackTrace<Problem, Solution> trace)
    {
        super(trace);
    }

    @Override
    protected State<Problem> popOpenList()
    {
        return ((Queue<State<Problem>>)this.openList).poll();
    }

    @Override
    protected void addToOpenList(State<Problem> state)
    {
        ((Queue<State<Problem>>)this.openList).add(state);
    }

    @Override
    protected void newSearcher()
    {
        this.openList=new LinkedList<>();
    }

    @Override
    protected boolean isByPriority() {
        return false;
    }
}
