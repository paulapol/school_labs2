package ex1;

import java.io.Serializable;

public class Fir2 extends Thread implements Serializable {
    public void run() {
        double time = 0;
        while(true) {
            try {
                Thread.sleep(10);
                time += 0.01;
                System.out.println(this.getName() + " " + time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
