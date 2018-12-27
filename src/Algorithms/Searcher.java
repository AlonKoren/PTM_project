package Algorithms;

public interface Searcher <Problem,Solution>
{
    public Solution search(Searchable<Problem> s);
}
