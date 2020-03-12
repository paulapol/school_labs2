package ex1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends  Thread {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = null;
        Socket s = null;
        Fir1 f1=new Fir1();
        f1.start();
        try {
            ss= new ServerSocket(1900);  //creaza obiectul serversocket
            s = ss.accept();  //incepe asteptarea pe portul 1900 in momentul in care un client s-a conectat ss.accept() returneaza un socket care identifica conexiunea
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream()); //flux de citire
            Fir2 f2 = (Fir2)ois.readObject();
            f1.stop();
            f2.start();
            s.close();
            ss.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            ss.close();
            if (s != null)
                s.close();
        }

    }
}
