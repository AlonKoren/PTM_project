package server_side;

import Algorithms.BestFirstSearch;
import Algorithms.DFS;
import Algorithms.Searcher;
import Algorithms.State;
import Matrices.Direction;
import Matrices.Index;
import Matrices.Matrix;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

public class MyClientHandler implements ClientHandler
{

    private CacheManager<Matrix,String> CacheManager;

    public MyClientHandler(CacheManager<Matrix,String> CacheManager)
    {
        this.CacheManager=CacheManager;
    }

    @Override
    public void handleClient(InputStream inFromClient, OutputStream outToClient)
    {
        try
        {
            BufferedReader userInput = new BufferedReader(new InputStreamReader(inFromClient));
            PrintWriter outToScreen = new PrintWriter(outToClient);
            StringWriter stringWriter = new StringWriter();
            PrintWriter outToServer = new PrintWriter(stringWriter);
            readInputsAndSend(userInput, outToServer, "end");

            String stringClientInput = stringWriter.getBuffer().toString();
            System.out.println("from client:"+stringClientInput);
            String[] split = stringClientInput.split(System.lineSeparator());
            double[][] matrixInts = new double[split.length][split[0].split(",").length];
            for (int i = 0; i < matrixInts.length; i++) {
                String[] split1 = split[i].split(",");
                for (int j = 0; j < split1.length; j++) {
                    matrixInts[i][j] = Double.parseDouble(split1[j]);
                }
            }
            String enteryLine = userInput.readLine();
            String exitLine = userInput.readLine();
            Index enteryIndex = new Index(enteryLine);
            Index exitIndex = new Index(exitLine);


            Matrix matrix = new Matrix(matrixInts, enteryIndex, exitIndex);
            String solution=null;
            boolean flag = false;
            if(CacheManager.isSolutionExist(matrix))
            {
                try {
                    solution = CacheManager.getSolution(matrix);
                    flag=true;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    flag=false;
                }
            }
            if(!flag)
            {
                Searcher<Index, Collection<Direction>> indexIndexSearcher = new DFS<>((goalState, initialState) ->
                {
                    ArrayList<Direction> directions = new ArrayList<>();
                    State<Index> current = goalState;
                    do {
                        if (current.getCameFrom() != null) {
                            Index parent = current.getCameFrom().getState();
                            Direction direction = parent.directionToWhere(current.getState());
                            directions.add(0, direction);
                        }
                    } while ((current = current.getCameFrom()) != null);
                    //directions.forEach(direction -> System.out.print(direction.getDirection() + "->")); //for testing
                    return directions;
                });
                Collection<Direction> search = indexIndexSearcher.search(matrix);
                ArrayList<Direction> directions = new ArrayList<>(search);

                StringBuilder stringBuilder = new StringBuilder();

                for (int i = 0; i < directions.size(); i++) {
                    stringBuilder.append(directions.get(i));
                    if (i < directions.size() - 1)
                        stringBuilder.append(",");
                }
                solution= stringBuilder.toString();
                CacheManager.saveSolution(matrix, solution);
            }else {

            }

            outToScreen.println(solution);
            outToScreen.flush();


            ///end of my client handler
        }catch (IOException  e)
        {
            e.printStackTrace();
        }


    }
    private void readInputsAndSend(BufferedReader in, PrintWriter out, String exitStr)
    {
        try
        {
            String line;
            while(!(line=in.readLine()).equals(exitStr))
            {
                out.println(line);
                out.flush();
            }
        } catch (IOException e) { e.printStackTrace();}
    }
}
