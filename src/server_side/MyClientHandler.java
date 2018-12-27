package server_side;

import Algorithms.BackTrace;
import Algorithms.BestFirstSearch;
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
            String[] split = stringClientInput.split(System.lineSeparator());
            int[][] matrixInts = new int[split.length][split[0].split(",").length];
            for (int i = 0; i < matrixInts.length; i++) {
                String[] split1 = split[i].split(",");
                for (int j = 0; j < split1.length; j++) {
                    matrixInts[i][j] = Integer.parseInt(split1[j]);
                }
            }
            String enteryLine = userInput.readLine();
            String exitLine = userInput.readLine();
            Index enteryIndex = new Index(enteryLine);
            Index exitIndex = new Index(exitLine);


            Matrix matrix = new Matrix(matrixInts, enteryIndex, exitIndex);


            Searcher<Index, Collection<Direction>> indexIndexSearcher = new BestFirstSearch<>((goalState, initialState) ->
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
                directions.forEach(direction -> System.out.print(direction.getDirection() + "->")); //for testing
                return directions;
            });

            ArrayList<Direction> directions = new ArrayList<>(indexIndexSearcher.search(matrix));

            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < directions.size(); i++) {
                stringBuilder.append(directions.get(i));
                if (i < directions.size() - 1)
                    stringBuilder.append(",");
            }

            outToScreen.println(stringBuilder.toString());
            outToScreen.flush();


            ///end of my client handler
        }catch (IOException e)
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
