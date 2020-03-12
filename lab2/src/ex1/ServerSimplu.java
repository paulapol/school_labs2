package ex1;
import java.io.*;
import java.net.*;

public class ServerSimplu {
    public static void main(String[] args) throws IOException {
        ServerSocket ss=null;
        Socket socket=null;

        try{
            String line="";
            ss = new ServerSocket(1900); //creaza obiectul serversocket
            socket = ss.accept(); //incepe asteptarea peportul 1900
            //in momentul in care un client s-a  conectat ss.accept() returneaza un socket care identifica conexiunea creaza fluxurile de intrare iesire

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);

            while(!line.equals("END")){
                line = in.readLine(); //citeste datele de la client
                out.println("ECHO "+line); //trimite date la client
            }
        }catch(Exception e){
            e.printStackTrace();}
        finally{
            ss.close();
            if(socket!=null) socket.close();
        }
    }
}
