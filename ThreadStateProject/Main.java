class WorkerThread extends Thread {

    @Override
    public void run() {

        try {

            System.out.println(getName() + " is running...");

            // TIMED_WAITING state
            Thread.sleep(2000);

            System.out.println(getName() + " finished work.");

        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }
}

public class Main {

    public static void main(String[] args) throws InterruptedException {

        WorkerThread t1 = new WorkerThread();

        // NEW State
        System.out.println("State after creation: " + t1.getState());

        // RUNNABLE State
        t1.start();

        System.out.println("State after start(): " + t1.getState());

        // Give time to enter sleep
        Thread.sleep(500);

        // TIMED_WAITING State
        System.out.println("State during sleep(): " + t1.getState());

        // WAITING State using join()
        Thread monitorThread = new Thread(() -> {

            try {

                System.out.println("Monitor thread waiting for t1 to finish...");

                t1.join();

                System.out.println("Monitor thread resumed.");

            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        });

        monitorThread.start();

        Thread.sleep(500);

        System.out.println("Monitor thread state: " + monitorThread.getState());

        // Wait for threads to finish
        t1.join();
        monitorThread.join();

        // TERMINATED State
        System.out.println("Final state of t1: " + t1.getState());
    }
}