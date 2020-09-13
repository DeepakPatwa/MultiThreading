package reentrant_locks;

import java.awt.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Process {

    int count = 0;
    private Lock lock = new ReentrantLock(); // keeps count of how many locks are present and how many got unlocked

    private void increment(){
        for(int i=0;i<1000;i++)
            count++;
    }

    public void firstThread() throws InterruptedException{
        lock.lock();

        try {
            increment();
        }
        finally {
            lock.unlock();
        }
    }

    public void secondThread() throws InterruptedException{
        lock.lock();

        try {
            increment();
        }
        finally {
            lock.unlock();
        }
    }

    public void finished(){
        System.out.println("total count is: "+count);
    }
}
