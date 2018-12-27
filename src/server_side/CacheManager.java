package server_side;

import java.io.IOException;

public interface CacheManager <Problem,Solution>
{
	public boolean isSolutionExist(Problem problem);
	public Solution getSolution(Problem problem) throws IOException, ClassNotFoundException;
	public void saveSolution(Problem problem, Solution solution) throws IOException;
	
}
