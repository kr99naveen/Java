package interfaces;

public class JuspayThread2 extends Thread{
    private Payment payObj;

    JuspayThread2(String name, Payment payObj){
        super(name);
        this.payObj = payObj;
    }

    @Override
    public void run() {
        try{
            System.out.println(Thread.currentThread().getName() + " RUNNING");
            int count=0;
            while(count<3)
            {
                Thread.sleep(2000);
                try{
                    count++;
                    payObj.makePayment();
                    return;
                } catch (Exception e){
                    System.out.println("Exception during payment :::: "+e.getMessage());
                }

            }
        }catch (Exception e){
            System.out.println("Exception during payment :::: "+e.getMessage());
        }
    }
}
