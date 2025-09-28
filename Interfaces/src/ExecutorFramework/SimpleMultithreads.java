package ExecutorFramework;

public class SimpleMultithreads {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Thread[] threads = new Thread[9];
        for(int i=1;i<10;i++){
            int ele = i;
            threads[i-1] = new Thread(()->{
                long res = factorial(ele);
                System.out.println(res);
            });
            threads[i-1].start();
        }

        for(int i=0;i<9;i++){
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("time taken : "+(float)(System.currentTimeMillis()-startTime)/1000 + " seconds");
    }

    private static long factorial(int n){
        try {
//            Thread.sleep(1000);
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
