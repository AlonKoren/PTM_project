package Algorithms;

import Matrices.Index;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.PriorityQueue;

public class AStar <Problem,Solution> extends CommonSearcher <Problem,Solution>{

    public AStar(BackTrace<Problem, Solution> trace) {
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
    protected void newSearcher() {
        super.openList=new PriorityQueue<>();
    }

    @Override
    protected boolean isByPriority() {
        return true;
    }

    public interface Heuristic{
        public double cost(State s,State goalState);
    }

    Heuristic h = (s, goalState) -> {
        Index start=(Index)(s.getState());
        double x1=start.column;
        double y1=start.row;
        Index end=(Index)goalState.getState();
        double x2=end.column;
        double y2=end.row;
        return Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2,2));
    };

    @Override
    public Solution search(Searchable<Problem> s) {
        GoalSearchable<Problem> searchable=(GoalSearchable<Problem>)s;
        newSearcher();
        openList.add(s.getInitialState());
        HashSet<State> closedSet=new HashSet<State>();
        while(!openList.isEmpty())
        {
            State<Problem> n=popOpenList();
            closedSet.add(n);
            Collection<State<Problem>> successors=s.getAllPossibleStates(n);
            n.setCost(n.getCost()+h.cost(n,searchable.getGoalState()));
            if(s.isGoalState(n))
                return trace.backTrace(n, s.getInitialState());
            for(State<Problem> state : successors){
                state.setCost(state.getCost()+h.cost(state,searchable.getGoalState()));
                if(!closedSet.contains(state) && ! openList.contains(state)){
                    state.setCameFrom(n);
                    openList.add(state);
                }
                else if(n.getCost()+(state.getCost()-state.getCameFrom().getCost())<state.getCost()) {
                    if (openList.contains(state))
                        state.setCameFrom(n);
                    else {
                        state.setCameFrom(n);
                        closedSet.remove(state);
                        openList.add(state);
                    }
                }
            }
        }
        return null;
    }
}
