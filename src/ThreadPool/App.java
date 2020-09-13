package ThreadPool;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Process implements Runnable{
    private int id;

    Process(int id){
        this.id=id;
    }

    @Override
    public void run() {
    System.out.println("Starting id: "+id);

        try {
            Thread.sleep(2000);
            // can use TimeUnit.SECONDS.sleep(2); also it behaves same as above
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Completed id: "+id);
    }
}
//Thread Pool
//Most of the executor implementations use thread pools to execute tasks. A thread pool is nothing but a bunch of worker threads
// that exist separately from the Runnable or Callable tasks and is managed by the executor.

//Creating a thread is an expensive operation and it should be minimized. Having worker threads minimizes the overhead due to
// thread creation because executor service has to create the thread pool only once and then it can reuse the threads for executing any task.
//We already saw an example of a thread pool in the previous section called a fixed thread-pool.
//
//Tasks are submitted to a thread pool via an internal queue called the Blocking Queue. If there are more tasks than the
// number of active threads, they are inserted into the blocking queue for waiting until any thread becomes available. If the
// blocking queue is full than new tasks are rejected.
public class App {
    public static void main(String []args){
        // Note: We use the Executors.newSingleThreadExecutor() method to create an ExecutorService that
        // uses a single worker thread for executing tasks
        //Note: Executors.newFixedThreadPool(n) is used to create n threads
        ExecutorService executor = Executors.newFixedThreadPool(2);

        for(int i=0; i<5;i++){
            executor.submit(new Process(i));
        }
       executor.shutdown();//If you run this program, you will notice that the program never exits, because,
        // the executor service keeps listening for new tasks until we shut it down explicitly.
        //when shutdown() method is called on an executor service, it stops accepting new tasks,
        // waits for previously submitted tasks to execute, and then terminates the executor.
        //Note - shutdownNow() - this method interrupts the running task and shuts down the executor immediately.
        System.out.println("all tasks submitted");

        try {
            executor.awaitTermination(1, TimeUnit.DAYS); // waits for this this much of time then after only flow continiues
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("all tasks completed");
    }
}
