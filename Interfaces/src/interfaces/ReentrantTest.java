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

//here same instance of lock used two times, and it can be used, both are used kindoff separately
//because its an reentrant lock, i.e that can be re-enter
//a count is maintained for all
// first lock - count 1
//second lock - count 2
//first unlock - count 1
//first unlock - count 0
