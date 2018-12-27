package Algorithms;

import java.util.Stack;

public class DFS <Problem,Solution> extends CommonSearcher <Problem,Solution>
{
    public DFS(BackTrace<Problem, Solution> trace)
    {
        super(trace);
    }

    @Override
    protected State<Problem> popOpenList()
    {
        return ((Stack<State<Problem>>)this.openList).pop();
    }

    @Override
    protected void addToOpenList(State<Problem> state)
    {
        ((Stack<State<Problem>>)this.openList).push(state);
    }

    @Override
    protected void newSearcher()
    {
        this.openList=new Stack<>();
    }

    @Override
    protected boolean isByPriority() {
        return false;
    }
}
