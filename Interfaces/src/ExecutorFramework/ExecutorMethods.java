package ExecutorFramework;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorMethods {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> callableThread = executor.submit(() -> {
            System.out.println("naveen in callable");
            return 199;
        });

        Future<?> runnbaleThread = executor.submit(() -> System.out.println("naveen in runnbale"));
        Future<Integer> runnbaleThread2 = executor.submit(() -> System.out.println("naveen in runnable with return value provided"), 249);

        System.out.println(executor.isShutdown());
        try {
            System.out.println(callableThread.isDone());
            System.out.println(callableThread.get());
            System.out.println(runnbaleThread2.get());
            System.out.println(runnbaleThread.get());
            System.out.println(runnbaleThread.isCancelled());
            System.out.println(executor.isTerminated());

            Callable<Integer> c1 = () -> {
                Thread.sleep(100);
                return 11;
            };
            Callable<Integer> c2 = () -> {
                Thread.sleep(100);
                return 22;
            };
            Callable<Integer> c3 = () -> {
                Thread.sleep(100);
                return 33;
            };

            List<Future<Integer>> futures = executor.invokeAll(Arrays.asList(c1, c2, c3));
            List<Future<Integer>> futures2 = null;
            try {
                futures2 = executor.invokeAll(Arrays.asList(c1, c2, c3), 100, TimeUnit.MILLISECONDS);
            }catch (CancellationException e){
                System.out.println("exception ::: "+e);
            }

            Integer i = executor.invokeAny(Arrays.asList(c1, c2, c3));
            System.out.println("response from invokeAny "+i);

            executor.shutdown();

            for (Future<Integer> future : futures) {
                System.out.println(future.get());
            }

            for (Future<Integer> future : futures2) {
                System.out.println(future.get());
            }

            System.out.println(executor.isShutdown());
        }catch (Exception e) {
            System.out.println("exception in main catch :  "+e);
        }
    }
}
