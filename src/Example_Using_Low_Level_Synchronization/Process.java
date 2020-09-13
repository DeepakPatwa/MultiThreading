package Example_Using_Low_Level_Synchronization;

import java.util.LinkedList;
import java.util.Random;

public class Process {

    private LinkedList<Integer> list = new LinkedList<>();
    private final int LIMIT=10;
    private Object lock = new Object();

    public void producer() throws InterruptedException{
        int val=0;

        while(true){
            synchronized (lock){
                while(list.size() == LIMIT)
                    lock.wait(); // notice here Im putting lock.notice cuz we want to put wait method on lock obj... if we use this
                                 // .this (Process) object then its ok to use only wait()

                list.add(val++);
                lock.notify();
            }
        }
    }

    public void consumer() throws InterruptedException{
        Random random = new Random();

        while(true){

            synchronized (lock) {


                    while(list.size() == 0) { // this loop is to not blindly let it run once we get notify but we should check again if
                        lock.wait();        // list is not empty
                    }
                System.out.print("list size is: " + list.size());
                int value = list.removeFirst();
                System.out.println("; value is: " + value);
                lock.notify();
                }

            Thread.sleep(random.nextInt(1000));
        }
    }
}
