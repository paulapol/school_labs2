public class Main {
    public static void main(String[] args)  {

        Fir f1 = new Fir();
        Thread t1 = new Thread(f1,"counter1");
        t1.start();
    }
}
