package mytest;

import server_side.CacheManager;
import server_side.ClientHandler;
import server_side.Solver;

import java.io.*;

public class MyTestClientHandler implements ClientHandler
{
	private Solver<String, String> solver;
	private CacheManager<String, String> cm;

	public MyTestClientHandler(Solver<String, String> solver, CacheManager<String, String> cm)
	{
		this.solver = solver;
		this.cm = cm;
	}

	@Override
	public void handleClient(InputStream inFromClient, OutputStream outToClient) 
	{
		BufferedReader userInput=new BufferedReader(new InputStreamReader(inFromClient));
		PrintWriter outToScreen=new PrintWriter(outToClient);
		readInputsAndSend(userInput, outToScreen,"end");
	}

	private void readInputsAndSend(BufferedReader in, PrintWriter out, String exitStr)
	{
		try
		{
			String line;
			while(!(line=in.readLine()).equals(exitStr))
			{
				out.println(serverAnswerToClient(line));
				out.flush();
			}
		} catch (IOException e) { e.printStackTrace();}
	}

	private String serverAnswerToClient(String problem)
	{
		try
		{
			if (!cm.isSolutionExist(problem))
				cm.saveSolution(problem, solver.solve(problem));
			return cm.getSolution(problem);
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}


}
