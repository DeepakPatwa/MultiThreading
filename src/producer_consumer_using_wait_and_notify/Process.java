package producer_consumer_using_wait_and_notify;

import java.util.Scanner;

//The major difference is to wait to release the lock or monitor while sleep doesn't release any lock or monitor while waiting.
// Wait is used for inter-thread communication while sleep is used to introduce pause on execution.

//In case of wait() method thread goes in waiting state and it won't come back automatically until we call the notify() method
// (or notifyAll() if you have more then one thread in waiting state and you want to wake all of those thread). And you need
// synchronized or object lock or class lock to access the wait() or notify() or notifyAll() methods. And one more thing, the wait()
// method is used for inter-thread communication because if a thread goes in waiting state you'll need another thread to wake that thread.
//
//But in case of sleep() this is a method which is used to hold the process for few seconds or the time you wanted. Because you don't need to provoke any notify() or notifyAll() method to get that thread back. Or you don't need any other thread to call back that thread. Like if you want something should happen after few seconds like in a game after user's turn you want the user to wait until the computer plays then you can mention the sleep() method.
//
//And one more important difference which is asked often in interviews: sleep() belongs to Thread class and wait() belongs to Object class.
public class Process {

    public void producer() throws InterruptedException {

        synchronized (this){ // notice here that both producer and consumer are locked on same lock object (process obj) so only when notify
                            // opens the lock then same lock will be opened for the method where the wait is being used
            System.out.println("Producer Thread is running");
            wait();
            System.out.println("Resumed");
        }
    }

    public void consumer() throws InterruptedException {
        Scanner sc = new Scanner(System.in);

        Thread.sleep(2000);

        synchronized (this){ // same lock is used as producer
            System.out.println("Waiting for return key");
            sc.nextLine();
            System.out.println("Return key pressed");

            notify();
        }
    }
}
