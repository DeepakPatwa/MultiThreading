package ShutDownAThreadFromAnotherThread;


import java.util.Scanner;

class Processor extends Thread{
    private volatile boolean running=true;

    @Override
    public void run() {
        while(running){
            System.out.println("Hello");
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void shutDown(){
        running=false;
    }
}

public class App {

    public static void main(String []args){
    Processor pr1 = new Processor();

    pr1.start();

    System.out.println("Press Enter to stop");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();

        pr1.shutDown();
    }
}
