package countdown_latches;

// kind of Synchronizer
// it makes a thread to wait until other threads have finished their work same as Thread.join()
// but we cant use thread always like if we are creating threads using executor service then we cant use thread.join we have to use countdown latches
// and other senario can be like if want to let other thread to start only if 50% of threads are completed

//Creating an object of CountDownLatch by passing an int to its constructor (the count), is actually number of invited parties (threads) for an event.
//The thread, which is dependent on other threads to start processing, waits on until every other thread has called count down. All threads, which are waiting on await() proceed together once count down reaches to zero.
//countDown() method decrements the count and await() method blocks until count == 0

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Processor implements Runnable{
    private CountDownLatch latch; // CountDownLatch is synchronized in nature

    Processor(CountDownLatch latch){
        this.latch=latch;
    }

    @Override
    public void run() {
    System.out.println("Started.");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        latch.countDown();
    }
}

public class App {
    public static void main(String []args){
        CountDownLatch latch = new CountDownLatch(3);

        ExecutorService executor = Executors.newFixedThreadPool(3);

        for(int i=0;i<3;i++){
            executor.submit(new Processor(latch));
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Completed");
    }
}
