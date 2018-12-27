package server_side;

import java.io.*;

public class FileCacheManager implements CacheManager<String, String>
{

	@Override
	public boolean isSolutionExist(String problem)
	{
		// TODO Auto-generated method stub
		try
		{
			ObjectInputStream in=new ObjectInputStream(new FileInputStream(problem+".txt"));
			in.close();
			return true;
		} catch (IOException e)
		{
			return false;
		}
	}

	@Override
	public String getSolution(String problem) throws IOException, ClassNotFoundException
	{
		// TODO Auto-generated method stub
		ObjectInputStream in=new ObjectInputStream(new FileInputStream(problem+".txt"));
		String solution = (String) in.readObject();
		in.close();
		return solution;
	}

	@Override
	public void saveSolution(String problem, String solution) throws IOException
	{
		// TODO Auto-generated method stub
		ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(problem+".txt"));
		out.writeObject(solution);
		out.flush();
		out.close();
	}

}
