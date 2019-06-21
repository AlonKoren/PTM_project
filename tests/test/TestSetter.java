package test;

import mytest.MyTestClientHandler;
import server_side.*;

public class TestSetter {
	

	static Server server;
	
	public static void runServer(int port)
	{
		server=new MySerialServer();
		server.open(port,new MyTestClientHandler(new StringReverser(),new FileCacheManager()));
	}

	public static void stopServer()
	{
		server.stop();
	}


}
