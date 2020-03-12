package exercitiu;

import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static java.lang.System.out;

public class Client extends JFrame implements ActionListener{
    PrintWriter pw;
    BufferedReader br;
    JTextArea  taMessages;
    JTextField tfInput;
    JButton btnSend;
    Socket client;

    public Client(String servername) throws Exception {
        client  = new Socket(servername,9999);
        br = new BufferedReader( new InputStreamReader( client.getInputStream()) ) ;
        pw = new PrintWriter(client.getOutputStream(),true);
        buildInterface();
        new MessagesThread().start();  // create thread to listen for messages
    }

    public void buildInterface() {
        btnSend = new JButton("Send");
        taMessages = new JTextArea();
        taMessages.setRows(10);
        taMessages.setColumns(50);
        taMessages.setEditable(false);
        tfInput  = new JTextField(50);
        JScrollPane sp = new JScrollPane(taMessages, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(sp,"Center");
        JPanel bp = new JPanel( new FlowLayout());
        bp.add(tfInput);
        bp.add(btnSend);
        add(bp,"South");
        btnSend.addActionListener(this);
        //setSize(500,300);
        setVisible(true);
        pack(); //packs the components closely together->it sizes the frame so that all its contents are at or above their preferred sizes.
    }

    public void actionPerformed(ActionEvent evt) {
            pw.println(tfInput.getText());
            tfInput.setText("");
    }

    public static void main(String args[]) {
        String servername = "192.168.1.4"; //192.168.0.105
        try {
            new Client(servername);
        } catch(Exception ex) {
            out.println( "Error --> " + ex.getMessage());
        }
    }

    class  MessagesThread extends Thread {
        public void run() {
            String line;
            try {
                while(true) {
                    line = br.readLine();
                    taMessages.append(line + "\n");
                }
            } catch(Exception ex) {}
        }
    }
}