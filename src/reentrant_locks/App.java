package reentrant_locks;

public class App {

    public static void main(String []args) throws InterruptedException {
        final Process process = new Process();

        Thread t1= new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    process.firstThread();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    process.secondThread();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        process.finished();
    }
}
