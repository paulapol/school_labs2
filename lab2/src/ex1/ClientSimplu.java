package ex1;

import java.net.*;
import java.io.*;

public class ClientSimplu {
    public static void main(String[] args)throws Exception{

        Socket socket=null;
        try {
            //creare obiect address care identifica adresa serverului
            InetAddress address =InetAddress.getByName("192.168.1.4"); //se putea utiliza varianta alternativa: InetAddress.getByName("127.0.0.1")
            socket = new Socket(address,1900);

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); //citirea de la tastatura
            // Output is automatically flushed by PrintWriter:
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true); //trimiterea la server

            for(int i = 0; i < 10; i ++) {
                out.println("mesaj " + i);
                String str = in.readLine(); //trimite mesaj
                System.out.println(str); //asteapta raspuns
            }
            out.println("END"); //trimite mesaj care determina serverul sa inchida conexiunea
        }
        catch (Exception ex) {
            ex.printStackTrace();}
        finally{
            socket.close();
        }
    }
}
