package interfaces;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


//It allows:
//
//Multiple readers to access the resource simultaneously (as long as no writer holds the lock)
//
//Only one writer at a time (and when writing, no readers are allowed)
//
//This improves performance in scenarios where reads are much more frequent than writes.

public class ReadWriteLockTest {

    private int count = 0;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private  final Lock writeLock = lock.writeLock();

    private ReentrantLock normalLock = new ReentrantLock();

    public void increment(){
      writeLock.lock();
//        normalLock.lock();
        try{
            System.out.println("Performing write ops :: "+Thread.currentThread().getName());
            count++;
            Thread.sleep(2000);
            System.out.println("Write performed");
        }catch (Exception e){
            System.out.println("exception occurred while write op :: "+e);
        }finally {
            writeLock.unlock();
//            normalLock.unlock();
        }
    }

    public void getCount(){
//        readLock.lock();;
        normalLock.lock();
        try{
            System.out.println("reading ops : "+Thread.currentThread().getName()+" value -> "+count);
            //this sleep will give no effect as multiple reads are allowed given
            //that no writing is perormed, so both read threads will run simultnaeously
            //with no gap of this time
            //BUT if you use normal lock, it gonna give a of this time in read threads
            //:)
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            readLock.unlock();
//            normalLock.unlock();
        }

    }

    public static void main(String[] args) throws InterruptedException{
        ReadWriteLockTest rwObj = new ReadWriteLockTest();

        Runnable readTask = new Runnable() {
            @Override
            public void run() {
//                for(int i=0;i<10;i++){
                    System.out.println("read ops initiated");
                    rwObj.getCount();
//                }
            }
        };

        Runnable writeTask = new Runnable() {
            @Override
            public void run() {
//                for(int i=0;i<10;i++){
                    System.out.println("write ops initiated");
                    rwObj.increment();
//                }
            }
        };

        Thread t1 = new Thread(writeTask, "t1");
        Thread t2 = new Thread(readTask, "t2");
        Thread t3 = new Thread(readTask, "t3");

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

    }

}
