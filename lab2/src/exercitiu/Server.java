package exercitiu;

import java.io.*;
import java.util.*;
import java.net.*;
import static java.lang.System.out;

public class Server {
    Vector<HandleClient> clients = new Vector<HandleClient>();

    public void process() throws Exception  {
        ServerSocket server = new ServerSocket(9999,10);
        out.println("Server Started...");
        while( true) {
            Socket client = server.accept();
            HandleClient c = new HandleClient(client);
            clients.add(c);
        }
    }
    public static void main(String args[] ) throws Exception {
        new Server().process();
    }

    public void broadcast( String message)  {
        for ( HandleClient c : clients )
                c.sendMessage(message);
    }

    class  HandleClient extends Thread {
        String name = "";
        BufferedReader input;
        PrintWriter output;

        public HandleClient(Socket  client) throws Exception {
            input = new BufferedReader( new InputStreamReader( client.getInputStream())) ;
            output = new PrintWriter ( client.getOutputStream(),true);
            name  = input.readLine();
            start();
        }

        public void sendMessage(String  msg)  {
            output.println( msg);
        }
        public void run()  {
            String line;
            try    {
                while(true)   {
                    line = input.readLine();
                    broadcast(line);
                }
            }
            catch(Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}