package CountdownLatch;

import java.util.concurrent.*;

public class CountdownLatch {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int noOfServices=4;
        CountDownLatch latch = new CountDownLatch(noOfServices);

        ExecutorService executor = Executors.newFixedThreadPool(noOfServices);
        Future f1 = executor.submit(new DependentService(latch));
        Future f2 = executor.submit(new DependentService(latch));
        Future f3 = executor.submit(new DependentService(latch));

        //also using manual thread
        Thread t1 = new Thread(()->{
            try {
//                Thread.sleep(2000);
                Thread.sleep(4000);
                System.out.println("manual thread also ran");
                latch.countDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t1.start();

//        f1.get();
//        f2.get();
//        f3.get();
//        t1.join();

        //latch's await performing the same task as all individual get of futures collectively
        //and t1.join
//        normal wait method, wait till all complete
//        latch.await();

        //to wait for a limited time, if all ran okay, otherwise continue main
        latch.await(2,TimeUnit.SECONDS);

        System.out.println("main thread continue");
        executor.shutdown();
//        executor.shutdownNow();

    }
}

class DependentService implements Runnable{

    private final CountDownLatch latch;
    DependentService(CountDownLatch latch){
        this.latch=latch;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
            System.out.println("Executor Thread ran "+Thread.currentThread().getName());
            latch.countDown();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            latch.countDown();
        }
    }
}


