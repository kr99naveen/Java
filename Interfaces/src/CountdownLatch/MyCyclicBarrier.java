package CountdownLatch;


import java.util.concurrent.*;

//countdownlatch cannot be resued, so we use CyclicBarrier in that case
public class MyCyclicBarrier {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int noOfServices=4;
//        CountDownLatch latch = new CountDownLatch(noOfServices);
//        using cyclic barrier here
        CyclicBarrier barrier = new CyclicBarrier(noOfServices);
        ExecutorService executor = Executors.newFixedThreadPool(noOfServices);
        Future f1 = executor.submit(new DependentService2(barrier));
        Future f2 = executor.submit(new DependentService2(barrier));
        Future f3 = executor.submit(new DependentService2(barrier));

        //also using manual thread
        Thread t1 = new Thread(()->{
            try {
                Thread.sleep(500);
                System.out.println("manual thread also ran");
                //barrier waits until all the members of the barrier has reached this await
                barrier.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (BrokenBarrierException e){
                throw new RuntimeException(e);
            }
        });
        t1.start();

//        NOTE : CyclicBarrier does not block main thread
//        and thus main use-case of Cyclic Barrier is to make sure all threads  reach a particular
//        point before any of them proceed WITHOUT blocking the main thread
        System.out.println("main thread continue");
        executor.shutdown();
    }
}

class DependentService2 implements Runnable{

    private final CyclicBarrier barrier;
    DependentService2(CyclicBarrier barrier){
        this.barrier=barrier;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println("Executor Thread ran "+Thread.currentThread().getName());
            //barrier waits until all the members of the barrier has reached this await
            //saare dost hall mein tab jaaenge jab saare aa jaayein gate pr
            barrier.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }
}
