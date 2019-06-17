package Algorithms;

public interface GoalSearchable<Problem> extends Searchable<Problem> {
    State<Problem> getGoalState();
}
