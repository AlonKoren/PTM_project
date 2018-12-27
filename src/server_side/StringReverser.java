package server_side;

public class StringReverser implements Solver<String, String>
{
    @Override
    public String solve(String problem)
    {
        return new StringBuffer(problem).reverse().toString();
    }
}
