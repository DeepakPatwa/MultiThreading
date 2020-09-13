package Multiple_Locks_Using_Syschronized_Block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class App {

    private Random random = new Random();
    List<Integer> list1 = new ArrayList<Integer>();
    List<Integer> list2 = new ArrayList<Integer>();

    private Object lock1 = new Object();
    private Object lock2 = new Object();

    //****public synchronized void stageOne(){ ****// making the method sysncronized will add lock on the whole object (will put lock on whole class)
    // so if one thread is using any of the method in the app object then other threads have to wait even if they want to use other than that method also
    // and because of that the whole process will be slow thats y better to use synchronized blocks rather then synchronized method
    public void stageOne(){
        synchronized (lock1) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list1.add(random.nextInt(100));
        }
    }

    public synchronized void stageTwo(){
        synchronized (lock2) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            list2.add(random.nextInt(100));
        }
    }

    public void process(){
        for(int i=0; i<1000; i++){
            stageOne();
            stageTwo();
        }
    }


    public static void main(String []args){
        System.out.println("Starting.......");
        App app = new App();

        long start = System.currentTimeMillis();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                app.process();
            }
        });

        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                app.process();
            }
        });

        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        long end = System.currentTimeMillis();
        System.out.println("time taken to complete the whole process is:" + (end-start));
        System.out.println("   list 1 size: "+ app.list1.size() + "list 2 size: "+ app.list2.size());
    }
}
