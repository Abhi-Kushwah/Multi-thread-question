class OddThread extends Thread {

    @Override
    public void run() {

        for (int i = 1; i <= 10; i += 2) {

            System.out.println("Odd: " + i);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class EvenThread extends Thread {

    @Override
    public void run() {

        for (int i = 2; i <= 10; i += 2) {

            System.out.println("Even: " + i);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Main {

    public static void main(String[] args) {

        OddThread odd = new OddThread();

        EvenThread even = new EvenThread();

        odd.start();

        even.start();
    }
}