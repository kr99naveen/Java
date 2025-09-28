package ExecutorFramework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorFramework {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        ExecutorService executor = Executors.newFixedThreadPool(9); //making thread pool of fixed number of threads to handle our tasks asynchronously
        for(int i=1;i<10;i++){
            int ele = i;
            executor.submit(()->{
                long res = factorial(ele);
                System.out.println(res);
                return 1;
            });
        }

//        executor.submit(()-> System.out.println("naveen"),"s");

        executor.shutdown(); //if this line not added, executor will continue to run even if threads completed their tasks
        //and once the executor has been shutdowm, you can not assign tasks it, IT HAS BEEN SHUT DOWN

        try {
            while(!executor.awaitTermination(1, TimeUnit.MILLISECONDS))
                System.out.println("waiting");
            //this just like kind of thread.join in manual thread, waits until all the threads completed their tasks, hover and see more details
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("time taken for factorials : "+(float)(System.currentTimeMillis()-startTime)/1000 + " seconds");
    }

    private static long factorial(int n){
        try {
//            Thread.sleep(500);
            long ans=1;
            for(int i=1;i<=n;i++){
                ans*=i;
            }
            return ans;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
