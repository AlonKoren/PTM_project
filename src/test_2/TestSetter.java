package test_2;

import Matrices.Matrix;
import server_side.*;

public class TestSetter {
	

	static Server server;
	
	public static void runServer(int port)
	{
		// put the code here that runs your server
		server=new MySerialServer(); // initialize
		server.open(port,new MyClientHandler(new FileCacheManager<Matrix,String>()));
	}

	public static void stopServer() {
		server.stop();
	}
	

}
