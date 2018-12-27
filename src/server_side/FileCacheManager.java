package server_side;

import java.io.*;
import java.util.HashMap;

public class FileCacheManager<Problem,Solution>  implements CacheManager<Problem,Solution>
{
    HashMap<Problem,Solution> problemSolutionHashMap=new HashMap<>();
	@Override
	public boolean isSolutionExist(Problem problem) {
		// TODO Auto-generated method stub
		try
		{
            boolean b = problemSolutionHashMap.containsKey(problem);
            if(b)
                return true;
            ObjectInputStream in=new ObjectInputStream(new FileInputStream(problem+".txt"));
			in.close();
			return true;
		} catch (IOException e)
		{
			return false;
		}
	}

	@Override
	public Solution getSolution(Problem problem) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
        Solution solution = problemSolutionHashMap.getOrDefault(problem, null);
        if(solution ==null){
            ObjectInputStream in=new ObjectInputStream(new FileInputStream(problem+".txt"));
            solution = (Solution) in.readObject();
            problemSolutionHashMap.put(problem,solution); //if the server go down and up again
            in.close();
        }
		return solution;
	}

	@Override
	public void saveSolution(Problem problem, Solution solution) throws IOException {
		// TODO Auto-generated method stub
        problemSolutionHashMap.put(problem,solution);
        ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(problem+".txt"));
		out.writeObject(solution);
		out.flush();
		out.close();
	}
}
