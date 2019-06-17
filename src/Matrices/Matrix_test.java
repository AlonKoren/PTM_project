package Matrices;

import Algorithms.*;

import java.util.ArrayList;
import java.util.Random;


public class Matrix_test
{
    public static void main(String[] args)
    {
        Random random=new Random();
        for (int c = 0; c < 10; c++)
        {
            for(int n=0;n<=10;n++)
            {
                double[][] matrix1= new double[n][n];
                System.out.println(n+" on "+ n);
                for (int i = 0; i < n ; i++)
                {
                    for (int j = 0; j < n; j++)
                    {
                        matrix1[i][j] = random.nextInt(100);
                        System.out.print(matrix1[i][j]);
                        if(j<n-1)
                            System.out.print(",");
                    }
                    System.out.println();
                }
                System.out.println();

                Matrix matrix=new Matrix(matrix1,new Index(0,0),new Index(matrix1.length-1,matrix1.length-1));
                BackTrace<Index, Index> backTrace= (goalState, initialState) -> {
                    ArrayList<State<Index>> arrayList=new ArrayList<>();
                    State<Index> current=goalState;
                    do {
                        arrayList.add(0,current);
                    }while ((current=current.getCameFrom())!=null);
                    arrayList.forEach(indexState -> System.out.print(indexState.toString()+"->"));
                    System.out.println("goalState: "+goalState.getCost());
                    return goalState.getState();
                };
                BestFirstSearch<Index,Index> matrixMatrixBestFirstSearch=new BestFirstSearch<>(backTrace);
                System.out.println("BestFirstSearch ");
                Index index1 = matrixMatrixBestFirstSearch.search(matrix);

                BFS<Index,Index> indexIndexBFS=new BFS<>(backTrace);
                System.out.println("BFS ");
                Index index2 = indexIndexBFS.search(matrix);

                DFS<Index,Index> indexIndexDFS=new DFS<>(backTrace);
                System.out.println("DFS ");
                Index index3 = indexIndexDFS.search(matrix);

                System.out.println("--------------------------------");
                System.out.println("--------------------------------");
            }
            System.out.println("**********************      next round       **********************");



        }


    }
}
