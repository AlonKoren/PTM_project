package Algorithms;

public class State <T> implements Comparable<State<T>>
{
    private T state;    // the state represented by a T
    private double cost;     // cost to reach this state
    private State<T> cameFrom;  // the state we came from to this state

    public State(T state)//init state
    {
        this.state = state;
        this.cost=0;
        this.cameFrom=null;
    }

    public State(T state, double cost, State<T> cameFrom)
    {
        this.state = state;
        this.cost = cost;
        this.cameFrom = cameFrom;
    }

    public T getState()
    {
        return state;
    }

    public void setState(T state)
    {
        this.state = state;
    }

    public double getCost()
    {
        return cost;
    }

    public void setCost(double cost)
    {
        this.cost = cost;
    }

    public State<T> getCameFrom()
    {
        return cameFrom;
    }

    public void setCameFrom(State<T> cameFrom)
    {
        this.cameFrom = cameFrom;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        State<T> state1 = (State<T>) o;

        return state.equals(state1.state);
    }

    @Override
    public int hashCode()
    {
        return state.hashCode();
    }

    @Override
    public String toString()
    {
        int mycost = (int)this.cost;
        if(this.getCameFrom()!=null)
            mycost-=this.getCameFrom().getCost();
        return state.toString()+"="+(mycost);
    }

    @Override
    public int compareTo(State<T> o)
    {
        return Double.compare(this.cost,o.cost);
    }
}
