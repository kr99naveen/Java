package interfaces;

import java.util.concurrent.locks.ReentrantLock;

public class UnfiarLockTest {

    private final ReentrantLock unfairLock = new ReentrantLock();

    public void accessResource(){
        unfairLock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+" locked and accessing the resource");
            Thread.sleep(2000);
        }catch (Exception e){
            Thread.currentThread().interrupt();
        }finally {
            System.out.println(Thread.currentThread().getName()+" released the lock and resource");
            unfairLock.unlock();
        }
    }

    public static void main(String[] args) {

        UnfiarLockTest obj = new UnfiarLockTest();
        Runnable task = new Runnable() {
            @Override
            public void run() {
//                System.out.println( Thread.currentThread().getName() + " : Executing thread");
                obj.accessResource();
            }
        };

        Thread t1 = new Thread(task, "thread 1");
        Thread t2 = new Thread(task, "thread 2");
        Thread t3 = new Thread(task, "thread 3");
        t1.start();
        t2.start();
        t3.start();
    }

}
