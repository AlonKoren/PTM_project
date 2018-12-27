package boot;

import server_side.*;

public class Main
{
    public static void main(String[] args)
    {
        int port =Integer.parseInt(args[0]);
        Server server=new MySerialServer();
        ClientHandler clientHandler=new MyTestClientHandler(new StringReverser(),new FileCacheManager());
        server.open(port,clientHandler);
        server.stop();
    }
}
