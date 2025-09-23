package interfaces;

import java.util.concurrent.locks.ReentrantLock;

public class FairLockTest {

    //passing true in constructor makes the lock, fair i.e first come first serve
    //eg if thread 1 started before 2, then thread 1 will get the access first
    //while if not passed as true, it can unfair
    private final ReentrantLock fairLock = new ReentrantLock(true );

    public void accessResource(){
        fairLock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+" locked and accessing the resource");
            Thread.sleep(1000);
        }catch (Exception e){
            Thread.currentThread().interrupt();
        }finally {
            System.out.println(Thread.currentThread().getName()+" released the lock and resource");
            fairLock.unlock();
        }
    }

    public static void main(String[] args) {

        FairLockTest obj = new FairLockTest();
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
