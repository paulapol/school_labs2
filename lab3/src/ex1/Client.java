package ex1;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket s = null;
        try {
            InetAddress address = InetAddress.getByName("localhost");   //creare obiect address care identifica adresa serverului
            s = new Socket(address, 1900);
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream()); //flux scriere
            Fir2 f2 = new Fir2();
            oos.writeObject(f2);
            s.close();

        } catch (Exception ex) {
                ex.printStackTrace();
        }finally {
            s.close();
        }
    }
}
