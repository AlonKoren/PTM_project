package Matrices;

import java.util.Objects;

public class Index
{
    public int row;
    public int column;

    public Index(int row, int column)
    {
        this.row = row;
        this.column = column;
    }
    public Index(String index)
    {
        String[] split = index.split(",");
        this.row=Integer.parseInt(split[0]);
        this.column=Integer.parseInt(split[1]);
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


    public Direction directionToWhere(Index nextIndex)
    {
        Index current = this;
        if(current.row-1 == nextIndex.row && current.column == nextIndex.column)
            return Direction.Up;
        if(current.row+1 == nextIndex.row && current.column == nextIndex.column)
            return Direction.Down;
        if(current.column-1 == nextIndex.column && current.row == nextIndex.row)
            return Direction.Left;
        if(current.column+1 == nextIndex.column && current.row == nextIndex.row)
            return Direction.Right;
        throw new IllegalArgumentException("nextIndex not good");
    }


}
