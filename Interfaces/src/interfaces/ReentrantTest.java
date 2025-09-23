package interfaces;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantTest {

    private final ReentrantLock lock = new ReentrantLock();

    public void outerMethod(){
        lock.lock();
        try{
            System.out.println("Outer Method called");
            innerMethod();
        }finally {
            lock.unlock();
        }
    }

    public void innerMethod(){
        try{
            lock.lock();
            try{
                System.out.println("Inner Method called ");
            }finally {
                lock.unlock();
            }
        }catch (Exception e){
            System.out.println("exception ::: "+e);
        }

    }

    public static void main(String[] args) {
        ReentrantTest obj = new ReentrantTest();
        obj.outerMethod();
    }
}
