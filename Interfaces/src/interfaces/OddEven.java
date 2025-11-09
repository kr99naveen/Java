package interfaces;

public class OddEven {

 public static class Printer {
     private boolean isOddTurn;
     private int num=0;

     public synchronized void printOdd() throws InterruptedException {
         while (!isOddTurn) {
             wait();
         }

         System.out.println(num++);
         isOddTurn = false;
         notify();
     }

     public synchronized void printEven() throws InterruptedException {
         while (isOddTurn) {
             wait();
         }
         System.out.println(num++);
         isOddTurn = true;
         notify();
     }
 }

 static class OddThread implements Runnable{
     private Printer printer;
     OddThread(Printer obj){
         this.printer = obj;
     }
     @Override
     public void run() {
         for(int i=1;i<100;i++){
             try {
                 printer.printOdd();
             } catch (InterruptedException e) {
                 throw new RuntimeException(e);
             }
         }
     }
 }

    static class EvenThread implements Runnable{
        private Printer printer;
        EvenThread(Printer obj){
            this.printer = obj;
        }
        @Override
        public void run() {
            for(int i=0;i<100;i++){
                try {
                    printer.printEven();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Printer printer = new Printer();
        Thread t1 = new Thread (new OddThread(printer));
        Thread t2 = new Thread(new EvenThread(printer));

        t1.start();
        t2.start();
//        t1.join();
//        t2.join();
        System.out.println("printed");
    }
}
