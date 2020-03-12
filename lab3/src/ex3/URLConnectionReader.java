package ex3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class URLConnectionReader {
    public static void main(String[] args) throws Exception {
        URL utcluj = new URL("http://www.utcluj.ro");
        URLConnection con = utcluj.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        con.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        in.close();
    }
}
