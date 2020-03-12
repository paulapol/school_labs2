package ex1;

import java.io.Serializable;

public class Fir1 extends Thread implements Serializable {
    public void run() {
        double time = 0;
        while (true) {
            try {
                time += 0.1;
                System.out.println(this.getName() + " " + time);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
