package Algorithms;

public interface BackTrace <Problem,Solution>
{
    public Solution backTrace(State<Problem> goalState,State<Problem> initialState );
}
