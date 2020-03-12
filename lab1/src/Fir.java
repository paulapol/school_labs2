import javax.swing.*;

public class Fir extends JFrame implements Runnable {
    JTextField txt;
    float limit;

    public Fir() {
        this.limit = 0;
        setTitle("Counter");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initialize();
        setVisible(true);
    }

    void initialize() {
        this.setLayout(null);
        txt = new JTextField("0");
        txt.setBounds(102, 20, 80, 20);
        add(txt);
    }

    @Override
    public void run() {
        while (limit < 8.0) {
            try {
                Thread.sleep(100);
                limit += 0.1;
                if (limit > 5.0 && limit < 5.1 ) {
                    Fir f = new Fir();
                    Thread t1 = new Thread(f,"counter2");
                    t1.start();
                }
                txt.setText(String.format("%.1f", (limit)));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.setVisible(false);
        dispose(); // face parte din clasa JFrame si are ca efect inchiderea ferestrei curente
    }
}
