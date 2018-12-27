package test_2;

import server_side.*;

public class TestSetter {
	

	static Server server;
	
	public static void runServer(int port)
	{
		// put the code here that runs your server
		server=new MySerialServer(); // initialize
		server.open(port,new MyClientHandler());
	}

	public static void stopServer() {
		server.stop();
	}
	

}
