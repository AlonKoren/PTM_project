package server_side;

import Algorithms.Searchable;
import Algorithms.Searcher;

public class SolverAdapter<Problem,Solution> implements Solver<Searchable<Problem>,Solution>
{
    private Searcher<Problem,Solution> problemSolutionSearcher;

    public SolverAdapter(Searcher<Problem, Solution> problemSolutionSearcher)
    {
        this.problemSolutionSearcher = problemSolutionSearcher;
    }

    @Override
    public Solution solve(Searchable<Problem> problemSearchable)
    {
        return problemSolutionSearcher.search(problemSearchable);
    }
}
