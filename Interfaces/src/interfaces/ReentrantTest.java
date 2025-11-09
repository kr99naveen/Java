package interfaces;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantTest {

    private final ReentrantLock lock = new ReentrantLock();

    public void outerMethod(int caller){
        lock.lock();
        try{
            System.out.println("Outer Method called by : "+caller);
            Thread.sleep(1000);
            innerMethod(caller);
            System.out.println("Locked count that need to be unlocked :: "+lock.getHoldCount());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public void innerMethod(int caller){
        try{
            lock.lock();
//            lock.lock();
//            lock.lock();
            try{
                System.out.println("Inner Method called by : "+caller);
            }finally {
                lock.unlock();
            }
        }catch (Exception e){
            System.out.println("exception ::: "+e);
        }

    }

    public static void main(String[] args) {
        ReentrantTest obj = new ReentrantTest();

        Thread t1 = new Thread(()->{
            obj.outerMethod(1);
        });
        Thread t2 = new Thread(()->{
            obj.outerMethod(2);
        });

        t1.start();
        t2.start();
    }
}

//here same instance of lock used two times, and it can be used, both are used kindoff separately
//because its an reentrant lock, i.e that can be re-enter
//a count is maintained for all
// first lock - count 1
//second lock - count 2
//first unlock - count 1
//first unlock - count 0
