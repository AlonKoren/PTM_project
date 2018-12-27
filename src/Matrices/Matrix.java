package Matrices;

import Algorithms.Searchable;
import Algorithms.State;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;


class Index
{
    public int row;
    public int column;

    public Index(int row, int column)
    {
        this.row = row;
        this.column = column;
    }

    @Override
    public String toString()
    {
        return "(" + row +
                "," + column +
                ')';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Index index = (Index) o;
        return row == index.row && column == index.column;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(row, column);
    }
}

public class Matrix implements Searchable<Index>
{
    private int rows;
    private int columns;
    private int[][] matrix;


    public Matrix()
    {
        this(new int[0][0]);
    }

    public Matrix(int[][] matrix)
    {
        this(matrix.length,
                (matrix.length>0)?matrix[0].length:0,
                matrix);
    }

    private Matrix(int rows, int columns, int[][] matrix)
    {
        this.rows = rows;
        this.columns = columns;
        this.matrix = matrix;
    }

    @Override
    public State<Index> getInitialState()
    {
        double cost=0;
        try
        {
            cost=this.matrix[0][0];
        }catch (Exception ignore){}
        return new State<>(new Index(0,0),cost,null);
    }

    @Override
    public boolean isGoalState(State<Index> state)
    {
        return(state.getState().row==rows-1 && state.getState().column==columns-1);
    }

    @Override
    public Collection<State<Index>> getAllPossibleStates(State<Index> s)
    {
        Collection<State<Index>> states= new ArrayList<>();
        Index index=s.getState();
        int currentRow=index.row;
        int currentColumn=index.column;
        if(currentRow-1 >= 0)
            states.add(new State<>(new Index(currentRow-1,currentColumn),s.getCost()+this.matrix[currentRow-1][currentColumn],s));
        if(currentRow+1 < rows)
                states.add(new State<>(new Index(currentRow+1,currentColumn),s.getCost()+this.matrix[currentRow+1][currentColumn],s));
        if(currentColumn-1 >= 0)
            states.add(new State<>(new Index(currentRow,currentColumn-1),s.getCost()+this.matrix[currentRow][currentColumn-1],s));
        if(currentColumn+1 < columns)
            states.add(new State<>(new Index(currentRow,currentColumn+1),s.getCost()+this.matrix[currentRow][currentColumn+1],s));
        return states;
    }
}
